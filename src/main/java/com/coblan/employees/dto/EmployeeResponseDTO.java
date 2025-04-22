package com.coblan.employees.dto;


public record EmployeeResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String documentNumber,
        String email,
        String phone,
        String cuit,
        String address,
        Boolean active
) {}
