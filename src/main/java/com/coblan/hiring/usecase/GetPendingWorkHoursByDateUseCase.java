package com.coblan.hiring.usecase;


import com.coblan.hiring.dto.WorkHourResponseDTO;
import java.time.LocalDate;
import java.util.List;

public interface GetPendingWorkHoursByDateUseCase {
    List<WorkHourResponseDTO> execute(LocalDate date);
}