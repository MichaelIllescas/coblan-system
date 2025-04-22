package com.coblan.services.usecase;

import com.coblan.services.dto.ServiceRequestDTO;
import com.coblan.services.dto.ServiceResponseDTO;

public interface UpdateServiceUseCase {

    ServiceResponseDTO execute(Long id, ServiceRequestDTO dto);
}
