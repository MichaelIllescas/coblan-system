package com.coblan.hiring.dto;

import java.time.LocalDate;
import java.util.Set;

public record HiringResponseDTO(
        Long id,
        LocalDate startDate,
        Set<ScheduleEntryDTO> schedule,
        String status,
        String cancellationReason,
        Long customerId,
        String customerName,
        Long serviceId,
        String serviceName,
        Long employeeId,
        String employeeName,
        Double totalAmount
) {}
