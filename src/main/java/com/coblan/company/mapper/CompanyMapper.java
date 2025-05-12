package com.coblan.company.mapper;

import com.coblan.company.domain.Company;
import com.coblan.company.dto.CompanyRequestDTO;
import com.coblan.company.dto.CompanyResponseDTO;
import com.coblan.users.domain.User;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public Company toEntity(CompanyRequestDTO dto) {
        Company company = new Company();
        company.setBusinessName(dto.businessName());
        company.setCuit(dto.cuit());
        company.setIvaCondition(dto.ivaCondition());
        company.setFiscalAddress(dto.fiscalAddress());
        company.setTradeName(dto.tradeName());
        company.setEmail(dto.email());
        company.setPhone(dto.phone());
        return company;
    }

    public void updateEntity(Company company, CompanyRequestDTO dto) {
        company.setBusinessName(dto.businessName());
        company.setCuit(dto.cuit());
        company.setIvaCondition(dto.ivaCondition());
        company.setFiscalAddress(dto.fiscalAddress());
        company.setTradeName(dto.tradeName());
        company.setEmail(dto.email());
        company.setPhone(dto.phone());
    }

    public CompanyResponseDTO toResponseDTO(Company company) {
        return new CompanyResponseDTO(
                company.getId(),
                company.getBusinessName(),
                company.getCuit(),
                company.getIvaCondition(),
                company.getFiscalAddress(),
                company.getTradeName(),
                company.getEmail(),
                company.getPhone()
        );
    }
}
