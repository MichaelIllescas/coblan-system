package com.coblan.hiring.dto;

import jakarta.validation.constraints.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

public record ScheduleEntryDTO(

        @NotNull
        DayOfWeek day,

        @NotNull
        LocalTime hour
) {}
