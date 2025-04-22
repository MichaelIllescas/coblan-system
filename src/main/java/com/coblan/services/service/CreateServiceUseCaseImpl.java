package com.coblan.services.service;



import com.coblan.services.domain.Service;
import com.coblan.services.dto.ServiceRequestDTO;
import com.coblan.services.dto.ServiceResponseDTO;
import com.coblan.services.mapper.ServiceMapper;
import com.coblan.services.repository.ServiceRepository;
import com.coblan.services.usecase.CreateServiceUseCase;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class CreateServiceUseCaseImpl implements CreateServiceUseCase {

    private final ServiceRepository serviceRepository;

    @Override
    public ServiceResponseDTO execute(ServiceRequestDTO dto) {
        Service service = ServiceMapper.toEntity(dto);
        Service saved = serviceRepository.save(service);
        return ServiceMapper.toDTO(saved);
    }
}

