package com.coblan.hiring.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record WorkHourResponseDTO(
        Long id,
        LocalDate date,
        LocalTime hour,
        LocalDate confirmationDate,
        LocalTime confirmationHour,
        Integer duration,
        String type,          // STANDARD o EXTRA
        String status,        // PENDING, CONFIRMED, CANCELLED
        String employeeName,
        String customerName,
        Long employeeId,
        String note,
        Double price
) {}
