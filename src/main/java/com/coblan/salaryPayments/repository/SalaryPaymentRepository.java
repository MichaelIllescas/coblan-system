package com.coblan.salaryPayments.repository;

import com.coblan.salaryPayments.domain.SalaryPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface SalaryPaymentRepository extends JpaRepository<SalaryPayment, Long> {

    @Query("SELECT SUM(s.amount) FROM SalaryPayment s WHERE MONTH(s.paymentDate) = :month AND YEAR(s.paymentDate) = :year")
    Optional<BigDecimal> sumSalariesByMonthAndYear(@Param("month") int month, @Param("year") int year);

    @Query("SELECT SUM(s.amount) FROM SalaryPayment s WHERE YEAR(s.paymentDate) = :year")
    Optional<BigDecimal> sumSalariesByYear(@Param("year") int year);

    @Query("""
    SELECT SUM(s.amount) FROM SalaryPayment s
    WHERE YEAR(s.paymentDate    ) = :year AND MONTH(s.paymentDate) = :month
            """)
    Optional<BigDecimal> sumSalariesByYearAndMonth(@Param("year") int year, @Param("month") int month);

}
