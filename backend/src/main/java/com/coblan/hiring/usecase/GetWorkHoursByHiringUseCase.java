package com.coblan.hiring.usecase;

import com.coblan.hiring.dto.WorkHourResponseDTO;
import java.util.List;

public interface GetWorkHoursByHiringUseCase {
    List<WorkHourResponseDTO> execute(Long hiringId);
}