package com.coblan.hiring.impl;

import com.coblan.hiring.domain.Hiring;
import com.coblan.hiring.dto.HiringResponseDTO;
import com.coblan.hiring.mapper.HiringMapper;
import com.coblan.hiring.repository.HiringRepository;
import com.coblan.hiring.usecase.GetMonthlyHiringsListUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class getMonthlyHiringsListUseCaseImpl implements GetMonthlyHiringsListUseCase {

    private final HiringRepository hiringRepository;

    @Override
    public List<HiringResponseDTO> execute(int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

         List<Hiring> hirings = hiringRepository.findByStartDateBetween(start, end);
        return hirings.stream().map(HiringMapper::toResponseDTO).toList();
    }


}
