package com.coblan.salaryPayments.mapper;

import com.coblan.employees.domain.Employee;
import com.coblan.salaryPayments.domain.SalaryPayment;
import com.coblan.salaryPayments.dto.SalaryPaymentRequestDTO;
import com.coblan.salaryPayments.dto.SalaryPaymentResponseDTO;

public class SalaryPaymentMapper {

    /**
     * Convierte un DTO de solicitud en una entidad SalaryPayment.
     *
     * @param dto       DTO con los datos del pago.
     * @param employee  Empleado al que se le realizó el pago.
     * @return Entidad SalaryPayment.
     */
    public static SalaryPayment toEntity(SalaryPaymentRequestDTO dto, Employee employee) {
        return SalaryPayment.builder()
                .employee(employee)
                .amount(dto.amount())
                .paymentDate(dto.paymentDate())
                .period(dto.period())
                .note(dto.note())
                .build();
    }

    /**
     * Convierte una entidad SalaryPayment a un DTO de respuesta.
     *
     * @param payment Entidad del pago.
     * @return SalaryPaymentResponseDTO.
     */
    public static SalaryPaymentResponseDTO toDTO(SalaryPayment payment) {
        String employeeFullName = payment.getEmployee().getFirstName() + " " + payment.getEmployee().getLastName();
        return new SalaryPaymentResponseDTO(
                payment.getId(),
                payment.getEmployee().getId(),
                employeeFullName,
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getPeriod(),
                payment.getNote()
        );
    }

    /**
     * Actualiza una entidad existente con los datos del DTO.
     *
     * @param existing  Entidad existente.
     * @param dto       Nuevos datos.
     * @param employee  Empleado actualizado.
     */
    public static void updateEntity(SalaryPayment existing, SalaryPaymentRequestDTO dto, Employee employee) {
        existing.setEmployee(employee);
        existing.setAmount(dto.amount());
        existing.setPaymentDate(dto.paymentDate());
        existing.setPeriod(dto.period());
        existing.setNote(dto.note());
    }
}
