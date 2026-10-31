package com.coblan.hiring.impl;

import com.coblan.hiring.dto.HiringResponseDTO;
import com.coblan.hiring.mapper.HiringMapper;
import com.coblan.hiring.repository.HiringRepository;
import com.coblan.hiring.usecase.GetHiringByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetHiringByIdUseCaseImpl  implements GetHiringByIdUseCase {

    private final HiringRepository hiringRepository;

    @Override
    public HiringResponseDTO execute(Long id) {
        return HiringMapper.toResponseDTO(hiringRepository.findById(id).get());
    }
}
