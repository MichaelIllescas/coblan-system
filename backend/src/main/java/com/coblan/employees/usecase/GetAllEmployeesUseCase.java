package com.coblan.employees.usecase;

import com.coblan.employees.dto.EmployeeResponseDTO;

import java.util.List;

public interface GetAllEmployeesUseCase {
    List<EmployeeResponseDTO> execute(Boolean onlyActive);
}