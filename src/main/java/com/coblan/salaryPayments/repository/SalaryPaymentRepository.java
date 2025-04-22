package com.coblan.salaryPayments.repository;

import com.coblan.salaryPayments.domain.SalaryPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryPaymentRepository extends JpaRepository<SalaryPayment, Long> {

}
