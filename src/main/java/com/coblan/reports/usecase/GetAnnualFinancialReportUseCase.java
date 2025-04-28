package com.coblan.reports.usecase;

import com.coblan.reports.dto.FinancialReportDTO;

public interface GetAnnualFinancialReportUseCase {
    FinancialReportDTO execute(int year);
}
