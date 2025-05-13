package com.coblan.reports.usecase;

import com.coblan.reports.dto.HiringReportDTO;

import java.util.List;

public interface GetMonthlyHiringsReportUseCase {
    List<HiringReportDTO> execute(int month, int year);
}