package com.coblan.hiring.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record WorkHourResponseDTO(
        Long id,
        LocalDate date,
        LocalTime hour,
        Integer duration,
        String type,          // STANDARD o EXTRA
        String status,        // PENDING, CONFIRMED, CANCELLED
        String employeeName,
        Long employeeId,
        String note
) {}
