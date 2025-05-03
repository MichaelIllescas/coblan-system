package com.coblan.hiring.repository;

import com.coblan.hiring.domain.Hiring;
import com.coblan.hiring.domain.HiringStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface HiringRepository  extends JpaRepository<Hiring, Long> {
    List<Hiring> findByStatus(HiringStatus status);

    List<Hiring> findByEmployeeIdAndStatus(Long employeeId, HiringStatus status);
    List<Hiring> findByStartDateBetween(LocalDate startDate, LocalDate endDate);


    @Query("SELECT h FROM Hiring h WHERE MONTH(h.startDate) = :month AND YEAR(h.startDate) = :year AND h.status = 'ACTIVE'")
    List<Hiring> findActiveHiringsByMonthAndYear(@Param("month") int month, @Param("year") int year);

    @Query("SELECT h FROM Hiring h WHERE YEAR(h.startDate) = :year AND h.status = 'ACTIVE'")
    List<Hiring> findActiveHiringsByYear(@Param("year") int year);
    @Query("""
    SELECT h FROM Hiring h
    WHERE YEAR(h.startDate) = :year AND MONTH(h.startDate) = :month
    AND h.cancellationdate IS NULL
    """)
    List<Hiring> findActiveHiringsByYearAndMonth(@Param("year") int year, @Param("month") int month);


}
