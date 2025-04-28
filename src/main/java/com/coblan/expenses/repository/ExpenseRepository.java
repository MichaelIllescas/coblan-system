package com.coblan.expenses.repository;

import com.coblan.expenses.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Repositorio JPA para gestionar la persistencia de gastos.
 */
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE MONTH(e.date) = :month AND YEAR(e.date) = :year")
    Optional<BigDecimal> sumExpensesByMonthAndYear(@Param("month") int month, @Param("year") int year);

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE YEAR(e.date) = :year")
    Optional<BigDecimal> sumExpensesByYear(@Param("year") int year);

}
