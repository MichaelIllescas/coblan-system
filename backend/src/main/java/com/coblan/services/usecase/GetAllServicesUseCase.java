package com.coblan.services.usecase;

import com.coblan.services.dto.ServiceResponseDTO;

import java.util.List;

public interface GetAllServicesUseCase {
    List<ServiceResponseDTO> execute();
}
