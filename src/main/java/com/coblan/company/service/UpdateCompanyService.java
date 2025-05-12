package com.coblan.company.service;

import com.coblan.company.domain.Company;
import com.coblan.company.dto.CompanyRequestDTO;
import com.coblan.company.dto.CompanyResponseDTO;
import com.coblan.company.mapper.CompanyMapper;
import com.coblan.company.repository.CompanyRepository;
import com.coblan.company.usecase.UpdateCompanyUseCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCompanyService implements UpdateCompanyUseCase {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public CompanyResponseDTO execute(CompanyRequestDTO dto) {
        Company company = companyRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new EntityNotFoundException("No hay una empresa registrada para actualizar"));

        companyMapper.updateEntity(company, dto);
        Company updated = companyRepository.save(company);

        return companyMapper.toResponseDTO(updated);
    }



}
