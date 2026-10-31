package com.coblan.salaryPayments.service;

import com.coblan.salaryPayments.repository.SalaryPaymentRepository;
import com.coblan.salaryPayments.useCase.DeleteSalaryPaymentUseCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteSalaryPaymentUseCaseImpl implements DeleteSalaryPaymentUseCase {

    private final SalaryPaymentRepository repository;

    @Override
    public void execute(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Pago de salario no encontrado con ID: " + id);
        }
        repository.deleteById(id);
    }
}
