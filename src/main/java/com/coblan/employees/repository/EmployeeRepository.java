package com.coblan.employees.repository;


import com.coblan.employees.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByDocumentNumber(String dni);

    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByCuit(String cuit);

    List<Employee> findAllByActive(Boolean active);
}
