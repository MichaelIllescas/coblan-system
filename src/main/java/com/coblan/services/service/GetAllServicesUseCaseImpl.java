package com.coblan.services.service;

import com.coblan.services.dto.ServiceResponseDTO;
import com.coblan.services.mapper.ServiceMapper;
import com.coblan.services.repository.ServiceRepository;
import com.coblan.services.usecase.GetAllServicesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllServicesUseCaseImpl implements GetAllServicesUseCase {
    private final ServiceRepository serviceRepository;


    @Override
    public List<ServiceResponseDTO> execute() {
       return serviceRepository.findAll().stream().map(ServiceMapper::toDTO).toList();
    }

}
