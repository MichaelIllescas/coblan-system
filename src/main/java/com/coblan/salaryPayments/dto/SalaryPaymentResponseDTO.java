package com.coblan.salaryPayments.dto;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

public record SalaryPaymentResponseDTO(
        Long id,
        Long employeeId,
        String employeeFullName,
        BigDecimal amount,
        LocalDate paymentDate,
        YearMonth period,
        String note
) {}
