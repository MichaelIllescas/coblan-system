package com.coblan.services.dto;

import com.coblan.services.domain.WorkHourStatus;

public record WorkHourRequestDTO(
        double price,
        WorkHourStatus status,
        String note
) {}