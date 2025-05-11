package com.coblan.company.service;

import com.coblan.company.domain.Company;
import com.coblan.company.dto.CompanyRequestDTO;
import com.coblan.company.dto.CompanyResponseDTO;
import com.coblan.company.mapper.CompanyMapper;
import com.coblan.company.repository.CompanyRepository;
import com.coblan.company.usecase.RegisterCompanyUseCase;
import com.coblan.users.domain.User;
import com.coblan.users.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterCompanyUseCaseImpl implements RegisterCompanyUseCase {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public CompanyResponseDTO execute(Long userId, CompanyRequestDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        if (companyRepository.existsByUserId(userId)) {
            throw new EntityExistsException("El usuario ya tiene registrada una empresa");
        }

        Company company = companyMapper.toEntity(dto, user);
        Company saved = companyRepository.save(company);
        return companyMapper.toResponseDTO(saved);
    }
}
