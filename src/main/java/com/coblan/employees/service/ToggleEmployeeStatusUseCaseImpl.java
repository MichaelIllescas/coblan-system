package com.coblan.employees.service;


import com.coblan.employees.domain.Employee;
import com.coblan.employees.repository.EmployeeRepository;
import com.coblan.employees.usecase.ToggleEmployeeStatusUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToggleEmployeeStatusUseCaseImpl implements ToggleEmployeeStatusUseCase {

    private final EmployeeRepository employeeRepository;

    @Override
    public void execute(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con ID: " + id));

        employee.setActive(!employee.getActive());
        employeeRepository.save(employee);
    }
}