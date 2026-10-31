package com.coblan.salaryPayments.service;

import com.coblan.employees.domain.Employee;
import com.coblan.employees.repository.EmployeeRepository;
import com.coblan.salaryPayments.domain.SalaryPayment;
import com.coblan.salaryPayments.dto.SalaryPaymentRequestDTO;
import com.coblan.salaryPayments.dto.SalaryPaymentResponseDTO;
import com.coblan.salaryPayments.mapper.SalaryPaymentMapper;
import com.coblan.salaryPayments.repository.SalaryPaymentRepository;
import com.coblan.salaryPayments.useCase.UpdateSalaryPaymentUseCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateSalaryPaymentUseCaseImpl implements UpdateSalaryPaymentUseCase {

    private final SalaryPaymentRepository repository;
    private final EmployeeRepository employeeRepository;

    @Override
    public SalaryPaymentResponseDTO execute(Long id, SalaryPaymentRequestDTO dto) {
        SalaryPayment existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pago de salario no encontrado con ID: " + id));
        Employee employee = employeeRepository.findById(dto.employeeId())
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado con ID: " + dto.employeeId()));

        SalaryPaymentMapper.updateEntity(existing, dto, employee);
        return SalaryPaymentMapper.toDTO(repository.save(existing));
    }
}
