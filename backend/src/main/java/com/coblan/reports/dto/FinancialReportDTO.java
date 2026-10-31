package com.coblan.reports.dto;

import java.math.BigDecimal;

public record FinancialReportDTO(
        BigDecimal totalIncome,
        BigDecimal totalExpenses,
        BigDecimal totalSalaries,
        BigDecimal balance
) {}
