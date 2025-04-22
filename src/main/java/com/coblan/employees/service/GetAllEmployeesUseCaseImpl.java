package com.coblan.employees.service;

import com.coblan.employees.dto.EmployeeResponseDTO;

import com.coblan.employees.mappers.EmployeeMapper;
import com.coblan.employees.repository.EmployeeRepository;
import com.coblan.employees.usecase.GetAllEmployeesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllEmployeesUseCaseImpl implements GetAllEmployeesUseCase {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeResponseDTO> execute(Boolean onlyActive) {
        return (onlyActive != null && onlyActive)
                ? employeeRepository.findAllByActive(true).stream().map(EmployeeMapper::toDTO).toList()
                : employeeRepository.findAll().stream().map(EmployeeMapper::toDTO).toList();
    }
}
