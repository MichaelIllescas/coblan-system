package com.coblan.hiring.task;

import com.coblan.hiring.domain.Hiring;
import com.coblan.hiring.domain.WorkHour;
import com.coblan.hiring.domain.WorkHourType;
import com.coblan.hiring.domain.HiringStatus;
import com.coblan.hiring.repository.HiringRepository;
import com.coblan.hiring.usecase.RenewHiringUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HiringRenewalTask {

    private final HiringRepository hiringRepository;
    private final RenewHiringUseCase renewHiringUseCase;

    /**
     * Revisa diariamente si alguna contratación activa ya completó su ciclo
     * y, en ese caso, genera una nueva contratación idéntica para continuar el servicio.
     */
    @Scheduled(cron = "0 0 2 * * *") // todos los días a las 2 AM
    public void renovarContratacionesSiCorresponde() {
        List<Hiring> activas = hiringRepository.findAll().stream()
                .filter(h -> h.getStatus() == HiringStatus.ACTIVE)
                .toList();

        for (Hiring hiring : activas) {
            int totalMinutes = hiring.getService().getMonthlyHours() * 60;

            int generatedMinutes = hiring.getWorkHours().stream()
                    .filter(w -> w.getType() == WorkHourType.STANDARD)
                    .mapToInt(WorkHour::getDuration)
                    .sum();

            if (generatedMinutes < totalMinutes) {
                System.out.printf("No se renovó Hiring ID %d: aún le faltan %d minutos para completar su ciclo.%n",
                        hiring.getId(), totalMinutes - generatedMinutes);
                continue;
            }

            try {
                renewHiringUseCase.renew(hiring);
                System.out.printf("Contratación ID %d renovada automáticamente.%n", hiring.getId());
            } catch (Exception e) {
                System.err.printf("Error al renovar Hiring ID %d: %s%n", hiring.getId(), e.getMessage());
            }
        }
    }
}
