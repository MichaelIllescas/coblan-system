package com.coblan.reports.impl;

import com.coblan.expenses.repository.ExpenseRepository;
import com.coblan.hiring.domain.Hiring;
import com.coblan.hiring.domain.WorkHour;
import com.coblan.hiring.repository.HiringRepository;
import com.coblan.hiring.repository.WorkHourRepository;
import com.coblan.reports.dto.AnnualProfitResponseDTO;
import com.coblan.reports.usecase.GetAnnualFinancialReportUseCase;
import com.coblan.salaryPayments.repository.SalaryPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;

@Service
@RequiredArgsConstructor
public class GetAnnualFinancialReportService implements GetAnnualFinancialReportUseCase {

    private final HiringRepository hiringRepository;
    private final WorkHourRepository workHourRepository;
    private final ExpenseRepository expenseRepository;
    private final SalaryPaymentRepository salaryPaymentRepository;

    @Override
    public AnnualProfitResponseDTO execute(int year) {
        Map<String, Double> monthlyProfits = new LinkedHashMap<>();

        for (int month = 1; month <= 12; month++) {
            // Ingresos
            BigDecimal activeIncome = hiringRepository.findActiveHiringsByMonthAndYear( month,year).stream()
                    .map(h -> BigDecimal.valueOf(h.getServicePriceAtHiring()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal cancelledIncome = workHourRepository.findConfirmedHoursOfCancelledHiringsByYearAndMonth(year, month).stream()
                    .map(this::calculateIncomePerHour)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal income = activeIncome.add(cancelledIncome);

            // Egresos y salarios
            BigDecimal expenses = expenseRepository.sumExpensesByYearAndMonth(year, month).orElse(BigDecimal.ZERO);
            BigDecimal salaries = salaryPaymentRepository.sumSalariesByYearAndMonth(year, month).orElse(BigDecimal.ZERO);

            // Ganancia = ingresos - egresos - salarios
            BigDecimal profit = income.subtract(expenses.add(salaries));

            String monthName = Month.of(month).getDisplayName(TextStyle.FULL, new Locale("es"));
            monthlyProfits.put(capitalize(monthName), profit.setScale(2, RoundingMode.HALF_UP).doubleValue());
        }

        return new AnnualProfitResponseDTO(monthlyProfits);
    }

    private BigDecimal calculateIncomePerHour(WorkHour workHour) {
        Hiring hiring = workHour.getHiring();
        BigDecimal monthlyAmount = BigDecimal.valueOf(hiring.getServicePriceAtHiring());
        Integer standardHours = hiring.getService().getMonthlyHours();

        if (standardHours == null || standardHours == 0) return BigDecimal.ZERO;

        BigDecimal valuePerHour = monthlyAmount.divide(BigDecimal.valueOf(standardHours), 2, RoundingMode.HALF_UP);
        BigDecimal hoursWorked = BigDecimal.valueOf(workHour.getDuration()).divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);

        return valuePerHour.multiply(hoursWorked);
    }

    private String capitalize(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
