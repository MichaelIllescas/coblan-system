package com.coblan.hiring.repository;

import com.coblan.hiring.domain.WorkHour;
import com.coblan.hiring.domain.WorkHourStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    boolean existsByEmployeeIdAndDateAndHour(Long employeeId, LocalDate date,   LocalTime hour);


    @Query("SELECT w FROM WorkHour w WHERE w.employee.id = :employeeId AND MONTH(w.date) = :month AND YEAR(w.date) = :year")
    List<WorkHour> findByEmployeeIdAndMonthAndYear(
            @Param("employeeId") Long employeeId,
            @Param("month") int month,
            @Param("year") int year);


    @Query("SELECT w FROM WorkHour w WHERE MONTH(w.date) = :month AND YEAR(w.date) = :year AND w.status = 'CONFIRMED'")
    List<WorkHour> findConfirmedWorkHoursByMonthAndYear(@Param("month") int month, @Param("year") int year);

    @Query("SELECT w FROM WorkHour w WHERE w.hiring.status = 'CANCELLED' AND MONTH(w.date) = :month AND YEAR(w.date) = :year AND w.status = 'CONFIRMED'")
    List<WorkHour> findConfirmedHoursOfCancelledHiringsByMonthAndYear(@Param("month") int month, @Param("year") int year);

    @Query("SELECT w FROM WorkHour w WHERE YEAR(w.date) = :year AND w.hiring.status = 'CANCELLED' AND w.status = 'CONFIRMED'")
    List<WorkHour> findConfirmedHoursOfCancelledHiringsByYear(@Param("year") int year);


    @Query("SELECT w FROM WorkHour w WHERE MONTH(w.date) = :month AND YEAR(w.date) = :year AND w.status = 'PENDING'")
    List<WorkHour> findByMonthAndYearAndStatusPending(int month, int year);

    @Query("SELECT w FROM WorkHour w WHERE w.date = :date AND w.status = 'PENDING'")
    List<WorkHour> findByDateAndStatusPending(LocalDate date);
    @Query("""
    SELECT wh FROM WorkHour wh
    WHERE wh.status = 'CONFIRMED'
    AND wh.hiring.cancellationdate IS NOT NULL
    AND YEAR(wh.date) = :year AND MONTH(wh.date) = :month
    """)
    List<WorkHour> findConfirmedHoursOfCancelledHiringsByYearAndMonth(@Param("year") int year, @Param("month") int month);

}
