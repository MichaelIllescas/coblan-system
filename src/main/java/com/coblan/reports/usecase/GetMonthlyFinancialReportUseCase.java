package com.coblan.reports.usecase;

import com.coblan.reports.dto.FinancialReportDTO;

public interface GetMonthlyFinancialReportUseCase {
    FinancialReportDTO execute(int month, int year);
}
