package com.coblan.company.usecase;

import com.coblan.company.dto.CompanyRequestDTO;
import com.coblan.company.dto.CompanyResponseDTO;

public interface RegisterCompanyUseCase {
    CompanyResponseDTO execute(Long userId, CompanyRequestDTO dto);
}