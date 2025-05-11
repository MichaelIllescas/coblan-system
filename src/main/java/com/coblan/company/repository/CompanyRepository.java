package com.coblan.company.repository;

import com.coblan.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    boolean existsByUserId(Long userId);

    Optional<Company> findByUserId(Long userId);
}
