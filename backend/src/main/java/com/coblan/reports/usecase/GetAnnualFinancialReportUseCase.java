package com.coblan.reports.usecase;

import com.coblan.reports.dto.AnnualProfitResponseDTO;
import com.coblan.reports.dto.FinancialReportDTO;

public interface GetAnnualFinancialReportUseCase {
    AnnualProfitResponseDTO execute(int year);
}
