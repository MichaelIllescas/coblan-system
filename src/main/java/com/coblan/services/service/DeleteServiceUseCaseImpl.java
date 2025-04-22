package com.coblan.services.service;

import com.coblan.services.repository.ServiceRepository;
import com.coblan.services.usecase.DeleteServiceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteServiceUseCaseImpl implements DeleteServiceUseCase {

    private final ServiceRepository serviceRepository;

    @Override
    public void execute(Long id) {
        if (!serviceRepository.existsById(id)) {
            throw new RuntimeException("Servicio no encontrado");
        }
        serviceRepository.deleteById(id);
    }
}
