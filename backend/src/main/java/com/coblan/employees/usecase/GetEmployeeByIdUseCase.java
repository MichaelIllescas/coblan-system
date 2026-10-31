package com.coblan.employees.usecase;


import com.coblan.employees.dto.EmployeeResponseDTO;

public interface GetEmployeeByIdUseCase {
    EmployeeResponseDTO execute(Long id);
}
