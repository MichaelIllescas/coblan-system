package com.coblan.employees.usecase;

import com.coblan.employees.dto.EmployeeRequestDTO;
import com.coblan.employees.dto.EmployeeResponseDTO;

public interface UpdateEmployeeUseCase {
    EmployeeResponseDTO execute(Long id, EmployeeRequestDTO requestDTO);
}