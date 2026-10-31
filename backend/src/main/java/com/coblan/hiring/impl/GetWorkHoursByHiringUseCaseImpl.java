package com.coblan.hiring.impl;


import com.coblan.hiring.dto.WorkHourResponseDTO;
import com.coblan.hiring.mapper.WorkHourMapper;
import com.coblan.hiring.repository.WorkHourRepository;
import com.coblan.hiring.usecase.GetWorkHoursByHiringUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetWorkHoursByHiringUseCaseImpl implements GetWorkHoursByHiringUseCase {

    private final WorkHourRepository workHourRepository;

    @Override
    public List<WorkHourResponseDTO> execute(Long hiringId) {
        return workHourRepository.findByHiringId(hiringId)
                .stream()
                .map(WorkHourMapper::toDTO)
                .collect(Collectors.toList());
    }
}