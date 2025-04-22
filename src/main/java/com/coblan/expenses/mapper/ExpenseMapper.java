package com.coblan.expenses.mapper;

import com.coblan.expenses.domain.Expense;
import com.coblan.expenses.dto.ExpenseRequestDTO;
import com.coblan.expenses.dto.ExpenseResponseDTO;

/**
 * Mapper para convertir entre entidad Expense y DTOs relacionados.
 */
public class ExpenseMapper {

    /**
     * Convierte un ExpenseRequestDTO a una entidad Expense.
     *
     * @param dto DTO con los datos del gasto.
     * @return Entidad Expense.
     */
    public static Expense toEntity(ExpenseRequestDTO dto) {
        return Expense.builder()
                .name(dto.name())
                .description(dto.description())
                .amount(dto.amount())
                .date(dto.date())
                .notes(dto.notes())
                .build();
    }

    /**
     * Convierte una entidad Expense a ExpenseResponseDTO.
     *
     * @param expense Entidad del gasto.
     * @return ExpenseResponseDTO.
     */
    public static ExpenseResponseDTO toDTO(Expense expense) {
        return new ExpenseResponseDTO(
                expense.getId(),
                expense.getName(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getDate(),
                expense.getNotes()
        );
    }

    /**
     * Actualiza los datos de una entidad Expense con los datos del DTO.
     *
     * @param expense Entidad existente a actualizar.
     * @param dto DTO con nuevos datos.
     */
    public static void updateEntity(Expense expense, ExpenseRequestDTO dto) {
        expense.setName(dto.name());
        expense.setDescription(dto.description());
        expense.setAmount(dto.amount());
        expense.setDate(dto.date());
        expense.setNotes(dto.notes());
    }
}
