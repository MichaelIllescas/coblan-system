package com.coblan.hiring.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record ConfirmWorkHourRequestDTO(
        Long workHourId,
        LocalDate newDate,          // Fecha real de realización
        LocalTime newHour,          // Hora real de inicio
        Long newEmployeeId,         // Puede ser null si no se reasigna
        String observation          // Puede ser null o vacío
) {}
