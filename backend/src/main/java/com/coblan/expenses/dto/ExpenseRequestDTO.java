package com.coblan.expenses.dto;


import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO para solicitudes de creación y actualización de gastos.
 */
public record ExpenseRequestDTO(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
        String name,

        @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
        String description,

        @NotNull(message = "El monto del gasto es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor a cero")
        @Digits(integer = 10, fraction = 2, message = "Formato del monto inválido")
        BigDecimal amount,

        @NotNull(message = "La fecha del gasto es obligatoria")
        @PastOrPresent(message = "La fecha no puede ser futura")
        LocalDate date,

        @Size(max = 500, message = "Las observaciones no pueden superar los 500 caracteres")
        String notes
) {}
