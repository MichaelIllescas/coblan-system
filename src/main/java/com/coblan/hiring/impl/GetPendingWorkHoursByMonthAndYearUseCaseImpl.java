package com.coblan.hiring.impl;


import com.coblan.hiring.domain.WorkHourStatus;
import com.coblan.hiring.dto.WorkHourResponseDTO;
import com.coblan.hiring.mapper.WorkHourMapper;
import com.coblan.hiring.repository.WorkHourRepository;
import com.coblan.hiring.usecase.GetPendingWorkHoursByMonthAndYearUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPendingWorkHoursByMonthAndYearUseCaseImpl implements GetPendingWorkHoursByMonthAndYearUseCase {

    private final WorkHourRepository repository;

    @Override
    public List<WorkHourResponseDTO> execute(int month, int year) {
        return repository.findByMonthAndYearAndStatusPending(month, year).stream()
                .map(WorkHourMapper::toDTO)
                .toList();
    }
}