package com.coblan.customers.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CustomerRequestDTO(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 50, message = "El nombre no debe superar los 50 caracteres")
        String firstName,

        @NotBlank(message = "El apellido es obligatorio")
        @Size(max = 50, message = "El apellido no debe superar los 50 caracteres")
        String lastName,

        @NotBlank(message = "El número de documento es obligatorio")
        @Pattern(regexp = "\\d{7,15}", message = "El número de documento debe tener entre 7 y 15 dígitos")
        String documentNumber,

        @NotBlank(message = "El teléfono es obligatorio")
        @Size(max = 20, message = "El número de teléfono no debe superar los 20 caracteres")
        String phone,

        @NotBlank(message = "El correo electrónico es obligatorio")
        @Email(message = "El correo electrónico debe ser válido")
        String email,

        @NotBlank(message = "La direccion es bligatoria")
        String address,

        boolean active

) {}
