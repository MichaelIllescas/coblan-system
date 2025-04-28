package com.coblan.reports.impl;

import com.coblan.hiring.repository.HiringRepository;
import com.coblan.reports.dto.FinancialReportDTO;
import com.coblan.reports.usecase.GetMonthlyFinancialReportUseCase;
import com.coblan.expenses.repository.ExpenseRepository;
import com.coblan.salaryPayments.repository.SalaryPaymentRepository;
import com.coblan.hiring.repository.WorkHourRepository;
import com.coblan.hiring.domain.WorkHour;
import com.coblan.hiring.domain.WorkHourType;
import com.coblan.hiring.domain.Hiring;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetMonthlyFinancialReportService implements GetMonthlyFinancialReportUseCase {

    private final WorkHourRepository workHourRepository;
    private final ExpenseRepository expenseRepository;
    private final SalaryPaymentRepository salaryPaymentRepository;
    private  final HiringRepository hiringRepository;

    @Override
    public FinancialReportDTO execute(int month, int year) {

        // 1. Buscar contrataciones activas
        List<Hiring> activeHirings = hiringRepository.findActiveHiringsByMonthAndYear(month, year);

        BigDecimal activeIncome = activeHirings.stream()
                .map(hiring -> BigDecimal.valueOf(hiring.getServicePriceAtHiring()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 2. Buscar horas confirmadas de contrataciones canceladas
        List<WorkHour> confirmedCancelledHours = workHourRepository.findConfirmedHoursOfCancelledHiringsByMonthAndYear(month, year);

        BigDecimal cancelledIncome = confirmedCancelledHours.stream()
                .map(this::calculateIncomePerHour)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 3. Sumar ambos ingresos
        BigDecimal totalIncome = activeIncome.add(cancelledIncome);

        // 4. Gastos
        BigDecimal totalExpenses = expenseRepository.sumExpensesByMonthAndYear(month, year)
                .orElse(BigDecimal.ZERO);

        // 5. Salarios
        BigDecimal totalSalaries = salaryPaymentRepository.sumSalariesByMonthAndYear(month, year)
                .orElse(BigDecimal.ZERO);

        // 6. Balance
        BigDecimal balance = totalIncome.subtract(totalExpenses.add(totalSalaries));

        return new FinancialReportDTO(totalIncome, totalExpenses, totalSalaries, balance);
    }

    private BigDecimal calculateIncomePerHour(WorkHour workHour) {
        Hiring hiring = workHour.getHiring();
        BigDecimal monthlyAmount = BigDecimal.valueOf(hiring.getService().getTotalAmount());
        Integer standardHours = hiring.getService().getMonthlyHours();

        if (standardHours == null || standardHours == 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal valuePerHour = monthlyAmount.divide(BigDecimal.valueOf(standardHours), 2, RoundingMode.HALF_UP);

        BigDecimal hoursWorked = BigDecimal.valueOf(workHour.getDuration()).divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);

        return valuePerHour.multiply(hoursWorked);
    }

}
