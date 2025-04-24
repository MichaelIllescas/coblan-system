package com.coblan.hiring.service;


import com.coblan.hiring.domain.*;
import com.coblan.hiring.repository.HiringRepository;
import com.coblan.hiring.usecase.GenerateInitialWorkHoursUseCase;
import com.coblan.hiring.usecase.RenewHiringUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RenewHiringService implements RenewHiringUseCase {

    private final HiringRepository hiringRepository;
    private final GenerateInitialWorkHoursUseCase workHourGenerator;

    @Override
    public Hiring renew(Hiring previousHiring) {
        List<WorkHour> hours = previousHiring.getWorkHours();

        if (hours.isEmpty()) {
            throw new IllegalStateException("La contratación no tiene horas de trabajo registradas.");
        }

        // Obtener la última hora generada y avanzar un día
        WorkHour last = hours.get(hours.size() - 1);
        LocalDate newStartDate = last.getDate().plusDays(1);

        // Crear nueva contratación clonando la anterior
        Hiring newHiring = Hiring.builder()
                .startDate(newStartDate)
                .schedule(previousHiring.getSchedule())
                .customer(previousHiring.getCustomer())
                .service(previousHiring.getService())
                .employee(previousHiring.getEmployee())
                .status(HiringStatus.ACTIVE)
                .servicePriceAtHiring(previousHiring.getService().getTotalAmount())
                .build();

        // Generar WorkHours para la nueva contratación
        List<WorkHour> newHours = workHourGenerator.generate(newHiring);
        newHours.forEach(wh -> wh.setHiring(newHiring));
        newHiring.setWorkHours(newHours);

        // Cambiar estado de la anterior si corresponde
        previousHiring.setStatus(HiringStatus.RENEWED);

        hiringRepository.save(previousHiring);
        return hiringRepository.save(newHiring);
    }
}