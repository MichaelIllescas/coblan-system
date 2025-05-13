package com.coblan.salaryPayments.useCase;


import com.coblan.salaryPayments.dto.SalaryPaymentResponseDTO;

public interface GetSalaryPaymentByIdUseCase {
    SalaryPaymentResponseDTO execute(Long id);
}