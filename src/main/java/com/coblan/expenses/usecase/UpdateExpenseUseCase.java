package com.coblan.expenses.usecase;

import com.coblan.expenses.dto.ExpenseRequestDTO;
import com.coblan.expenses.dto.ExpenseResponseDTO;

public interface UpdateExpenseUseCase {
    ExpenseResponseDTO execute(Long id, ExpenseRequestDTO dto);
}
