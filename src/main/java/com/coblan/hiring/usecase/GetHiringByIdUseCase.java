package com.coblan.hiring.usecase;

import com.coblan.hiring.dto.HiringResponseDTO;

public interface GetHiringByIdUseCase {
    public HiringResponseDTO execute(Long id);
}
