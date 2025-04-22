package com.coblan.expenses.usecase;

import com.coblan.expenses.dto.ExpenseRequestDTO;
import com.coblan.expenses.dto.ExpenseResponseDTO;

public interface CreateExpenseUseCase {
    ExpenseResponseDTO execute(ExpenseRequestDTO dto);
}
