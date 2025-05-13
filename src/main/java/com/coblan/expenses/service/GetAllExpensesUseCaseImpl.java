package com.coblan.expenses.service;

import com.coblan.expenses.dto.ExpenseResponseDTO;
import com.coblan.expenses.mapper.ExpenseMapper;
import com.coblan.expenses.repository.ExpenseRepository;
import com.coblan.expenses.usecase.GetAllExpensesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllExpensesUseCaseImpl implements GetAllExpensesUseCase {

    private final ExpenseRepository repository;

    @Override
    public List<ExpenseResponseDTO> execute() {
        return repository.findAll()
                .stream()
                .map(ExpenseMapper::toDTO)
                .toList();
    }
}
