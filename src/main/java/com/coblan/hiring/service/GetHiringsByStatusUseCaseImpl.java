package com.coblan.hiring.service;


import com.coblan.hiring.domain.HiringStatus;
import com.coblan.hiring.dto.HiringResponseDTO;
import com.coblan.hiring.mapper.HiringMapper;
import com.coblan.hiring.repository.HiringRepository;
import com.coblan.hiring.usecase.GetHiringsByStatusUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetHiringsByStatusUseCaseImpl implements GetHiringsByStatusUseCase {

    private final HiringRepository hiringRepository;

    @Override
    public List<HiringResponseDTO> execute(String status) {
        HiringStatus hiringStatus = HiringStatus.valueOf(status.toUpperCase());
        return hiringRepository.findByStatus(hiringStatus).stream()
                .map(HiringMapper::toResponseDTO)
                .toList();
    }
}