package com.coblan.services.service;

import com.coblan.services.dto.ServiceRequestDTO;
import com.coblan.services.dto.ServiceResponseDTO;
import com.coblan.services.mapper.ServiceMapper;
import com.coblan.services.repository.ServiceRepository;
import com.coblan.services.usecase.UpdateServiceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UpdateServiceUseCaseImpl implements UpdateServiceUseCase {

    private final ServiceRepository serviceRepository;

    @Override
    public ServiceResponseDTO execute(Long id, ServiceRequestDTO dto) {
        com.coblan.services.domain.Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        service.setName(dto.name());
        service.setMonthlyHours(dto.monthlyHours());
        service.setTotalAmount(dto.totalAmount());
        service.setNote(dto.note());

        com.coblan.services.domain.Service  updated = serviceRepository.save(service);
        return ServiceMapper.toDTO(updated);
    }
}
