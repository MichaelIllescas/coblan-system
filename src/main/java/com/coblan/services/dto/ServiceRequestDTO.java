package com.coblan.services.dto;


public record ServiceRequestDTO(
        String name,
        int monthlyHours,
        double totalAmount,
        String note
) {}
