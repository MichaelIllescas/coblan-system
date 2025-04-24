package com.coblan.hiring.usecase;

import com.coblan.hiring.dto.HiringRequestDTO;
import com.coblan.hiring.dto.HiringResponseDTO;

public interface RegisterHiringUseCase {
    HiringResponseDTO execute(HiringRequestDTO dto);
}