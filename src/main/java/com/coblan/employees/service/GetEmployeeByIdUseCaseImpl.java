package com.coblan.employees.service;



import com.coblan.employees.domain.Employee;
import com.coblan.employees.dto.EmployeeResponseDTO;
import com.coblan.employees.mappers.EmployeeMapper;
import com.coblan.employees.repository.EmployeeRepository;
import com.coblan.employees.usecase.GetEmployeeByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetEmployeeByIdUseCaseImpl implements GetEmployeeByIdUseCase {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDTO execute(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con ID: " + id));
        return EmployeeMapper.toDTO(employee);
    }
}
