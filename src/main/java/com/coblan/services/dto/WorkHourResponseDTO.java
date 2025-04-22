package com.coblan.services.dto;

import com.coblan.services.domain.WorkHourStatus;

public record WorkHourResponseDTO(
        Long id,
        double price,
        WorkHourStatus status,
        String note
) {}
