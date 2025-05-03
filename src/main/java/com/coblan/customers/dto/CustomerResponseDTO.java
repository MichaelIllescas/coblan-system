package com.coblan.customers.dto;

public record CustomerResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String documentNumber,
        String phone,
        String email,
        String address,
        boolean active
) {}
