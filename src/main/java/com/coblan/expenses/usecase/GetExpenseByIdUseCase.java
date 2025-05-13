package com.coblan.expenses.usecase;

import com.coblan.expenses.dto.ExpenseResponseDTO;
import java.util.List;

public interface GetExpenseByIdUseCase {
    ExpenseResponseDTO execute(Long id);
}
