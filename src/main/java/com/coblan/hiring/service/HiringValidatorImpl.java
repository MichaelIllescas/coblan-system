package com.coblan.hiring.service;


import com.coblan.hiring.domain.Hiring;
import com.coblan.hiring.domain.HiringStatus;
import com.coblan.hiring.repository.HiringRepository;
import com.coblan.hiring.usecase.HiringValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HiringValidatorImpl implements HiringValidator {

    private final HiringRepository hiringRepository;

    @Override
    public void validateScheduleConflict(Hiring newHiring) {
        Long employeeId = newHiring.getEmployee().getId();

        List<Hiring> existingHirings = hiringRepository.findByEmployeeIdAndStatus(employeeId, HiringStatus.ACTIVE);

        Set<String> existingSlots = existingHirings.stream()
                .flatMap(h -> h.getSchedule().stream())
                .map(s -> s.getDay() + "|" + s.getHour())
                .collect(Collectors.toSet());

        List<String> conflicts = newHiring.getSchedule().stream()
                .map(s -> s.getDay() + "|" + s.getHour())
                .filter(existingSlots::contains)
                .collect(Collectors.toList());

        if (!conflicts.isEmpty()) {
            throw new IllegalStateException("El empleado ya tiene asignada una contratación en los siguientes días y horarios: "
                    + String.join(", ", conflicts));
        }
    }
}
