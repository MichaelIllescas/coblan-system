package com.coblan.hiring.usecase;

import com.coblan.hiring.dto.HiringResponseDTO;

import java.util.List;

public interface GetAllHiringsUseCase {
    List<HiringResponseDTO> execute();
}