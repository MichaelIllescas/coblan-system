package com.coblan.expenses.usecase;

import com.coblan.expenses.dto.ExpenseResponseDTO;
import java.util.List;

public interface GetAllExpensesUseCase {
    List<ExpenseResponseDTO> execute();
}
