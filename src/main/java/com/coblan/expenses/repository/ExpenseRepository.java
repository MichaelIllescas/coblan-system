package com.coblan.expenses.repository;

import com.coblan.expenses.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para gestionar la persistencia de gastos.
 */
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
