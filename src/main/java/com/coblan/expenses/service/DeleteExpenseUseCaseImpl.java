package com.coblan.expenses.service;

import com.coblan.expenses.repository.ExpenseRepository;
import com.coblan.expenses.usecase.DeleteExpenseUseCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteExpenseUseCaseImpl implements DeleteExpenseUseCase {

    private final ExpenseRepository repository;

    @Override
    public void execute(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Gasto no encontrado con ID: " + id);
        }
        repository.deleteById(id);
    }
}
