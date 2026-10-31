package com.coblan.employees.service;


import com.coblan.employees.domain.Employee;
import com.coblan.employees.dto.EmployeeRequestDTO;
import com.coblan.employees.dto.EmployeeResponseDTO;
import com.coblan.employees.mappers.EmployeeMapper;
import com.coblan.employees.repository.EmployeeRepository;
import com.coblan.employees.usecase.UpdateEmployeeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateEmployeeUseCaseImpl implements UpdateEmployeeUseCase {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDTO execute(Long id, EmployeeRequestDTO requestDTO) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con ID: " + id));

        // Validaciones de unicidad si se modifica el campo
        if (!existingEmployee.getDocumentNumber().equals(requestDTO.documentNumber()) &&
                employeeRepository.findByDocumentNumber(requestDTO.documentNumber()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un empleado con el número de documento " + existingEmployee.getDocumentNumber());
        }

        if (!existingEmployee.getEmail().equals(requestDTO.email()) &&
                employeeRepository.findByEmail(requestDTO.email()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un empleado con el correo electrónico " + existingEmployee.getEmail());
        }

        if (requestDTO.cuit() != null &&
                (existingEmployee.getCuit() == null || !existingEmployee.getCuit().equals(requestDTO.cuit())) &&
                employeeRepository.findByCuit(requestDTO.cuit()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un empleado con el CUIT " + existingEmployee.getCuit());
        }

        // Actualizar campos
        EmployeeMapper.updateEntity(existingEmployee, requestDTO);

        return EmployeeMapper.toDTO(employeeRepository.save(existingEmployee));
    }
}