package com.coblan.salaryPayments.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

public record SalaryPaymentRequestDTO(

        @NotNull(message = "El ID del empleado es obligatorio")
        Long employeeId,

        @NotNull(message = "El monto del salario es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor a cero")
        @Digits(integer = 10, fraction = 2, message = "Formato del monto inválido")
        BigDecimal amount,

        @NotNull(message = "La fecha de pago es obligatoria")
        LocalDate paymentDate,

        @NotNull(message = "El período del salario es obligatorio")
        @JsonFormat(pattern = "yyyy-MM")
        YearMonth period,

        @Size(max = 500, message = "La nota no puede superar los 500 caracteres")
        String note
) {}
