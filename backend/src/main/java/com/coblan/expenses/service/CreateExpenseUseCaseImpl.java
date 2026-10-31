package com.coblan.expenses.service;

import com.coblan.expenses.domain.Expense;
import com.coblan.expenses.dto.ExpenseRequestDTO;
import com.coblan.expenses.dto.ExpenseResponseDTO;
import com.coblan.expenses.mapper.ExpenseMapper;
import com.coblan.expenses.repository.ExpenseRepository;
import com.coblan.expenses.usecase.CreateExpenseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateExpenseUseCaseImpl implements CreateExpenseUseCase {

    private final ExpenseRepository repository;

    @Override
    public ExpenseResponseDTO execute(ExpenseRequestDTO dto) {
        Expense expense = ExpenseMapper.toEntity(dto);
        Expense savedExpense = repository.save(expense);
        return ExpenseMapper.toDTO(savedExpense);
    }
}
