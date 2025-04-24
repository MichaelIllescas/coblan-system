package com.coblan.hiring.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record AddExtraWorkHourRequestDTO(

        @NotNull(message = "Debe especificarse la contratación")
        Long hiringId,

        @NotNull(message = "La fecha no puede estar vacía")
        @FutureOrPresent(message = "La fecha debe ser hoy o una futura")
        LocalDate date,

        @NotNull(message = "La hora no puede estar vacía")
        LocalTime time,

        Long employeeId, // opcional, puede ser null si no se asigna aún

        @NotNull(message = "Debe especificarse el precio de la hora extra")
        Double extraHourPrice

) {}
