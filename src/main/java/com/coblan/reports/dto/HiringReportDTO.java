package com.coblan.reports.dto;

import java.time.LocalDate;

public record HiringReportDTO(
        Long hiringId,
        String customerName,
        String serviceName,
        String employeeName,
        LocalDate startDate,
        LocalDate cancellationDate,
        String status,
        Double servicePriceAtHiring
) {}
