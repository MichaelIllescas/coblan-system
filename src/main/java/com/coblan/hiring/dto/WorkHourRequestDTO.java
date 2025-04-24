package com.coblan.hiring.dto;

import com.coblan.hiring.domain.WorkHourStatus;

public record WorkHourRequestDTO(
        double price,
        WorkHourStatus status,
        String note
) {}