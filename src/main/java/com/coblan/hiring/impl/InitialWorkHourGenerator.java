package com.coblan.hiring.impl;

import com.coblan.hiring.domain.*;
import com.coblan.hiring.usecase.GenerateInitialWorkHoursUseCase;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Genera automáticamente las horas de trabajo STANDARD iniciales
 * para una contratación a partir de la fecha de inicio y el cronograma definido.
 */
@Component
public class InitialWorkHourGenerator implements GenerateInitialWorkHoursUseCase {

    /**
     * Genera las WorkHours necesarias para cubrir el total de horas contratadas
     * en el primer ciclo mensual de una contratación.
     *
     * @param hiring Contratación con schedule, empleado y servicio
     * @return Lista de WorkHour generadas
     */
    public List<WorkHour> generate(Hiring hiring) {
        List<WorkHour> workHours = new ArrayList<>();

        LocalDate currentDate = hiring.getStartDate();
        Set<ScheduleEntry> schedule = hiring.getSchedule();

        int totalMinutes = hiring.getService().getMonthlyHours() * 60;
        int durationPerOccurrence = 60; // minutos por unidad

        double pricePerMinute = hiring.getService().getTotalAmount() / totalMinutes;
        double pricePerOccurrence = pricePerMinute * durationPerOccurrence;

        int accumulatedMinutes = 0;

        while (accumulatedMinutes < totalMinutes) {
            for (ScheduleEntry entry : schedule) {
                if (currentDate.getDayOfWeek() == entry.getDay()) {
                    if (accumulatedMinutes >= totalMinutes) break;

                    workHours.add(WorkHour.builder()
                            .date(currentDate)
                            .hour(entry.getHour())
                            .duration(durationPerOccurrence)
                            .employee(hiring.getEmployee())
                            .status(WorkHourStatus.PENDING)
                            .type(WorkHourType.STANDARD)
                            .price(pricePerOccurrence)
                            .note(null)
                            .build());

                    accumulatedMinutes += durationPerOccurrence;
                }
            }
            currentDate = currentDate.plusDays(1);
        }

        return workHours;
    }
}

