package com.coblan.hiring.impl;

import com.coblan.hiring.domain.WorkHour;
import com.coblan.hiring.dto.WorkHourResponseDTO;
import com.coblan.hiring.mapper.WorkHourMapper;
import com.coblan.hiring.repository.WorkHourRepository;
import com.coblan.hiring.usecase.GetWokedHoursByEmployeeIdAndMonthAndYear;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class GetWokedHoursByEmployeeIdAndMonthAndYearImpl implements GetWokedHoursByEmployeeIdAndMonthAndYear {

    private final WorkHourRepository workHourRepository;

    @Override
    public List<WorkHourResponseDTO> execute(Long emplooyeId, Integer month, Integer year) {
        List<WorkHour>hourList= workHourRepository.findByEmployeeIdAndMonthAndYear(emplooyeId, month, year);

        return  hourList.stream().map(WorkHourMapper::toDTO).toList();
    }
}
