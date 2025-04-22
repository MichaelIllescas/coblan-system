package com.coblan.expenses.service;

import com.coblan.expenses.domain.Expense;
import com.coblan.expenses.dto.ExpenseRequestDTO;
import com.coblan.expenses.dto.ExpenseResponseDTO;
import com.coblan.expenses.mapper.ExpenseMapper;
import com.coblan.expenses.repository.ExpenseRepository;
import com.coblan.expenses.usecase.UpdateExpenseUseCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateExpenseUseCaseImpl implements UpdateExpenseUseCase {

    private final ExpenseRepository repository;

    @Override
    public ExpenseResponseDTO execute(Long id, ExpenseRequestDTO dto) {
        Expense expense = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Gasto no encontrado con ID: " + id));

        ExpenseMapper.updateEntity(expense, dto);
        Expense updatedExpense = repository.save(expense);

        return ExpenseMapper.toDTO(updatedExpense);
    }
}
