package com.coblan.salaryPayments.service;

import com.coblan.salaryPayments.domain.SalaryPayment;
import com.coblan.salaryPayments.dto.SalaryPaymentResponseDTO;
import com.coblan.salaryPayments.mapper.SalaryPaymentMapper;
import com.coblan.salaryPayments.repository.SalaryPaymentRepository;
import com.coblan.salaryPayments.useCase.GetSalaryPaymentByIdUseCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetSalaryPaymentByIdUseCaseImpl implements GetSalaryPaymentByIdUseCase {

    private final SalaryPaymentRepository repository;

    @Override
    public SalaryPaymentResponseDTO execute(Long id) {
        SalaryPayment payment = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pago de salario no encontrado con ID: " + id));
        return SalaryPaymentMapper.toDTO(payment);
    }
}
