package com.coblan.hiring.impl;

import com.coblan.hiring.dto.WorkHourResponseDTO;
import com.coblan.hiring.mapper.WorkHourMapper;
import com.coblan.hiring.repository.WorkHourRepository;
import com.coblan.hiring.usecase.GetPendingWorkHoursByDateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPendingWorkHoursByDateUseCaseImpl implements GetPendingWorkHoursByDateUseCase {

    private final WorkHourRepository repository;

    @Override
    public List<WorkHourResponseDTO> execute(LocalDate date) {
        return repository.findByDateAndStatusPending(date).stream()
                .map(WorkHourMapper::toDTO)
                .toList();
    }
}
