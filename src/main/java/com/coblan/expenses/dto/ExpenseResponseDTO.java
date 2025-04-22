package com.coblan.expenses.dto;


import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO para responder solicitudes sobre gastos.
 */
public record ExpenseResponseDTO(
        Long id,
        String name,
        String description,
        BigDecimal amount,
        LocalDate date,
        String notes
) {}
