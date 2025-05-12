package com.coblan.company.service;


import com.coblan.company.domain.Company;
import com.coblan.company.dto.CompanyResponseDTO;
import com.coblan.company.mapper.CompanyMapper;
import com.coblan.company.repository.CompanyRepository;
import com.coblan.company.usecase.GetCompanyUseCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCompanyService implements GetCompanyUseCase {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public CompanyResponseDTO execute() {
        Company company = companyRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("No hay una empresa registrada en el sistema"));

        return companyMapper.toResponseDTO(company);
    }
}
