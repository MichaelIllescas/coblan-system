package com.coblan.salaryPayments.useCase;

import com.coblan.salaryPayments.dto.SalaryPaymentResponseDTO;
import java.util.List;

public interface GetAllSalaryPaymentsUseCase {
    List<SalaryPaymentResponseDTO> execute();
}
