package com.coblan.salaryPayments.useCase;

import com.coblan.salaryPayments.dto.SalaryPaymentRequestDTO;
import com.coblan.salaryPayments.dto.SalaryPaymentResponseDTO;

public interface UpdateSalaryPaymentUseCase {
    SalaryPaymentResponseDTO execute(Long id, SalaryPaymentRequestDTO dto);
}
