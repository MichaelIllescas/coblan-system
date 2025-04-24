package com.coblan.hiring.usecase;


public interface WorkHourValidator {
    void validateEmployeeAvailabilityForExtraHour(Long employeeId, java.time.LocalDate date, java.time.LocalTime time);
}