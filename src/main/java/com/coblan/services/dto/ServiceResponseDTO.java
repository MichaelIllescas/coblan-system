package com.coblan.services.dto;

import java.util.List;

public record ServiceResponseDTO(
        Long id,
        String name,
        int monthlyHours,
        double totalAmount,
        String note
) {}
