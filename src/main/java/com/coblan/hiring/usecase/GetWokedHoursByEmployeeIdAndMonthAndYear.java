package com.coblan.hiring.usecase;

import com.coblan.hiring.domain.WorkHour;
import com.coblan.hiring.dto.WorkHourResponseDTO;

import java.util.List;

public interface GetWokedHoursByEmployeeIdAndMonthAndYear {
    public List<WorkHourResponseDTO> execute(Long emplooyeId, Integer month, Integer year);
}
