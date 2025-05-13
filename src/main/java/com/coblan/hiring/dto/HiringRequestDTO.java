package com.coblan.hiring.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

public record HiringRequestDTO(

        @NotNull
        LocalDate startDate,

        @NotNull
        @Size(min = 1)
        Set<ScheduleEntryDTO> schedule, // día + hora

        @NotNull
        Long customerId,

        @NotNull
        Long serviceId,

        @NotNull
        Long employeeId
) {}
