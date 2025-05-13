package com.coblan.hiring.usecase;

import com.coblan.hiring.dto.AddExtraWorkHourRequestDTO;
import com.coblan.hiring.dto.WorkHourResponseDTO;

public interface AddExtraWorkHourUseCase {
    WorkHourResponseDTO execute(AddExtraWorkHourRequestDTO requestDTO);
}