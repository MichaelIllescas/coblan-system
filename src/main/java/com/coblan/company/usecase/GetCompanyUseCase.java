package com.coblan.company.usecase;

import com.coblan.company.dto.CompanyResponseDTO;

public interface GetCompanyUseCase {
    CompanyResponseDTO execute(Long userId);
}
