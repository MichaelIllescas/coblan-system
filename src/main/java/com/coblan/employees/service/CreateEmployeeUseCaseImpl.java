package com.coblan.employees.service;


import com.coblan.employees.domain.Employee;
import com.coblan.employees.dto.EmployeeRequestDTO;
import com.coblan.employees.dto.EmployeeResponseDTO;

import com.coblan.employees.mappers.EmployeeMapper;
import com.coblan.employees.repository.EmployeeRepository;
import com.coblan.employees.usecase.CreateEmployeeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateEmployeeUseCaseImpl implements CreateEmployeeUseCase {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDTO execute(EmployeeRequestDTO requestDTO) {

        // Validación de unicidad
        if (employeeRepository.findByDocumentNumber(requestDTO.documentNumber()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un empleado con el D.N.I. Nro. " + requestDTO.documentNumber() );
        }

        if (employeeRepository.findByEmail(requestDTO.email()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un empleado con el correo electrónico "+ requestDTO.email());
        }

        if (requestDTO.cuit() != null && employeeRepository.findByCuit(requestDTO.cuit()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un empleado con el CUIT "+  requestDTO.cuit() );
        }

        Employee employee = EmployeeMapper.toEntity(requestDTO);
        employee = employeeRepository.save(employee);

        return EmployeeMapper.toDTO(employee);
    }
}