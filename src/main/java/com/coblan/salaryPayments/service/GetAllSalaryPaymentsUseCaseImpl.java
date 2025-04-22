package com.coblan.salaryPayments.service;

import com.coblan.salaryPayments.dto.SalaryPaymentResponseDTO;
import com.coblan.salaryPayments.mapper.SalaryPaymentMapper;
import com.coblan.salaryPayments.repository.SalaryPaymentRepository;
import com.coblan.salaryPayments.useCase.GetAllSalaryPaymentsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllSalaryPaymentsUseCaseImpl implements GetAllSalaryPaymentsUseCase {

    private final SalaryPaymentRepository repository;

    @Override
    public List<SalaryPaymentResponseDTO> execute() {
        return repository.findAll().stream()
                .map(SalaryPaymentMapper::toDTO)
                .toList();
    }
}
