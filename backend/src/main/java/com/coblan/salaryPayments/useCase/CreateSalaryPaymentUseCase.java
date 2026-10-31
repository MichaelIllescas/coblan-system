package com.coblan.salaryPayments.useCase;


import com.coblan.salaryPayments.dto.SalaryPaymentRequestDTO;
import com.coblan.salaryPayments.dto.SalaryPaymentResponseDTO;

public interface CreateSalaryPaymentUseCase {
    SalaryPaymentResponseDTO execute(SalaryPaymentRequestDTO dto);
}
