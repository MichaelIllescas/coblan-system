package com.coblan.hiring.usecase;

import com.coblan.hiring.dto.WorkHourResponseDTO;
import java.util.List;

public interface GetPendingWorkHoursByMonthAndYearUseCase {
    List<WorkHourResponseDTO> execute(int month, int year);
}