package com.coblan.hiring.impl;


import com.coblan.hiring.repository.WorkHourRepository;
import com.coblan.hiring.usecase.WorkHourValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkHourValidatorImpl implements WorkHourValidator {

    private final WorkHourRepository workHourRepository;

    @Override
    public void validateEmployeeAvailabilityForExtraHour(Long employeeId, java.time.LocalDate date, java.time.LocalTime time) {
        boolean existsConflict = workHourRepository.existsByEmployeeIdAndDateAndHour(employeeId, date, time);
        if (existsConflict) {
            throw new IllegalStateException("El empleado ya tiene una hora registrada el " + date + " a las " + time + ".");
        }
    }
}