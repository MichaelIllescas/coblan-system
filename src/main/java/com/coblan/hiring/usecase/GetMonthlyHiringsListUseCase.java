package com.coblan.hiring.usecase;

import com.coblan.hiring.domain.Hiring;
import com.coblan.hiring.dto.HiringResponseDTO;

import java.util.List;

public interface GetMonthlyHiringsListUseCase {
    public List<HiringResponseDTO> execute(int year, int mont);
}
