package com.coblan.reports.impl;

import com.coblan.hiring.domain.Hiring;
import com.coblan.hiring.repository.HiringRepository;
import com.coblan.reports.dto.HiringReportDTO;

import com.coblan.reports.usecase.GetMonthlyHiringsReportUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@RequiredArgsConstructor
public class GetMonthlyHiringsReportUseCaseImpl implements GetMonthlyHiringsReportUseCase {

    private final HiringRepository hiringRepository;
    @Override
    public List<HiringReportDTO> execute(int month, int year) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<Hiring> hirings = hiringRepository.findByStartDateBetween(start, end);

        return hirings.stream().map(h -> new HiringReportDTO(
                h.getId(),
                h.getCustomer().getFirstName() +" "+ h.getCustomer().getLastName(),
                h.getService().getName(),
                h.getEmployee().getFirstName() +" "+ h.getEmployee().getLastName(),
                h.getStartDate(),
                h.getCancellationdate(),
                h.getStatus().name(),
                h.getServicePriceAtHiring()
        )).toList();
    }

}

