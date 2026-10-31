package com.coblan.expenses.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Representa un gasto realizado por la empresa.
 */
@Entity
@Table(name = "expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String name;

    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    private String description;

    @NotNull(message = "El monto del gasto es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor a cero")
    @Digits(integer = 10, fraction = 2, message = "Formato del monto inválido")
    private BigDecimal amount;

    @NotNull(message = "La fecha del gasto es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate date;

    @Size(max = 500, message = "Las observaciones no pueden superar los 500 caracteres")
    private String notes;
}
