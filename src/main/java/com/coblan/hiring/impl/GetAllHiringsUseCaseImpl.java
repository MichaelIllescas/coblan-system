package com.coblan.hiring.impl;

import com.coblan.hiring.dto.HiringResponseDTO;
import com.coblan.hiring.mapper.HiringMapper;
import com.coblan.hiring.repository.HiringRepository;
import com.coblan.hiring.usecase.GetAllHiringsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllHiringsUseCaseImpl implements GetAllHiringsUseCase {

    private final HiringRepository hiringRepository;

    @Override
    public List<HiringResponseDTO> execute() {
        return hiringRepository.findAll().stream()
                .map(HiringMapper::toResponseDTO)
                .toList();
    }
}
