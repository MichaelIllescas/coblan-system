package com.coblan.hiring.repository;

import com.coblan.hiring.domain.Hiring;
import com.coblan.hiring.domain.HiringStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface HiringRepository  extends JpaRepository<Hiring, Long> {
    List<Hiring> findByStatus(HiringStatus status);

    List<Hiring> findByEmployeeIdAndStatus(Long employeeId, HiringStatus status);
}
