package com.coblan.employees.dto;


import jakarta.validation.constraints.*;

public record EmployeeRequestDTO(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 50, message = "El nombre no debe tener más de 50 caracteres")
        String firstName,

        @NotBlank(message = "El apellido es obligatorio")
        @Size(max = 50, message = "El apellido no debe tener más de 50 caracteres")
        String lastName,

        @NotBlank(message = "El DNI es obligatorio")
        @Size(min = 7, max = 10, message = "El DNI debe tener entre 7 y 10 caracteres")
        String documentNumber,

        @NotBlank(message = "El correo electrónico es obligatorio")
        @Email(message = "El correo electrónico debe ser válido")
        String email,

        @NotBlank(message = "El número de teléfono es obligatorio")
        @Size(max = 20, message = "El número de teléfono no debe tener más de 20 caracteres")
        String phone,

        @Size(max = 20, message = "El CUIT no debe tener más de 20 caracteres")
        String cuit,

        @Size(max = 100, message = "La dirección no debe tener más de 100 caracteres")
        String address
) {}
