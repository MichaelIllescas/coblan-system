package com.coblan.hiring.impl;

import com.coblan.hiring.domain.*;
import com.coblan.hiring.repository.HiringRepository;
import com.coblan.hiring.usecase.CancelHiringUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CancelHiringUseCaseImpl implements CancelHiringUseCase {

    private final HiringRepository hiringRepository;

    @Override
    public void execute(Long hiringId, String cancellationReason) {
        Hiring hiring = hiringRepository.findById(hiringId)
                .orElseThrow(() -> new IllegalArgumentException("Contratación no encontrada"));

        if (hiring.getStatus() == HiringStatus.CANCELLED) {
            throw new IllegalStateException("La contratación ya está cancelada");
        }

        // Cambiar estado de la contratación
        hiring.setStatus(HiringStatus.CANCELLED);
        hiring.setCancellationReason(cancellationReason);
        hiring.setCancellationdate(LocalDate.now());

        for (WorkHour workHour : hiring.getWorkHours()) {
            if (workHour.getStatus().equals(WorkHourStatus.PENDING)) {
                workHour.setStatus(WorkHourStatus.CANCELLED);
            }
        }


        hiringRepository.save(hiring);
    }
}

