package com.coblan.hiring.repository;

import com.coblan.hiring.domain.WorkHour;
import com.coblan.hiring.domain.WorkHourStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkHourRepository extends JpaRepository<WorkHour, Long> {
    Optional<WorkHour> findByHiringIdAndEmployeeIdAndDateAndStatus(
            Long hiringId,
            Long employeeId,
            LocalDate date,
            WorkHourStatus status
    );

    List<WorkHour> findByHiringId(Long hiringId);
    boolean existsByEmployeeIdAndDateAndHour(Long employeeId, LocalDate date, LocalTime hour);

}
