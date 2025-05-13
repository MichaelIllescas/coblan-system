package com.coblan.salaryPayments.domain;


import com.coblan.employees.domain.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.YearMonth;

/**
 * Representa un pago de salario realizado a un empleado.
 */
@Entity
@Table(name = "salary_payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El empleado es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @NotNull(message = "El monto del salario es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor a cero")
    @Digits(integer = 10, fraction = 2, message = "Formato del monto inválido")
    private BigDecimal amount;

    @NotNull(message = "La fecha de pago es obligatoria")
    private java.time.LocalDate paymentDate;

    @NotNull(message = "El período es obligatorio")
    @JsonFormat(pattern = "yyyy-MM")
    private YearMonth period;

    @Size(max = 500, message = "La nota no puede superar los 500 caracteres")
    private String note;
}
