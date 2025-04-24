package com.coblan.hiring.service;

import com.coblan.hiring.domain.WorkHour;
import com.coblan.hiring.domain.WorkHourStatus;
import com.coblan.hiring.dto.ConfirmWorkHourRequestDTO;
import com.coblan.hiring.repository.WorkHourRepository;
import com.coblan.hiring.usecase.ConfirmWorkHourUseCase;
import com.coblan.employees.domain.Employee;
import com.coblan.employees.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConfirmWorkHourUseCaseImpl implements ConfirmWorkHourUseCase {

    private final WorkHourRepository workHourRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public void execute(ConfirmWorkHourRequestDTO request) {
        WorkHour workHour = workHourRepository.findById(request.workHourId())
                .orElseThrow(() -> new EntityNotFoundException("Hora no encontrada"));

        if (workHour.getStatus() != WorkHourStatus.PENDING) {
            throw new IllegalStateException("Solo se pueden confirmar horas en estado PENDING");
        }

        // Reasignación de empleado si corresponde
        if (request.newEmployeeId() != null &&
                !request.newEmployeeId().equals(workHour.getEmployee().getId())) {

            Employee newEmployee = employeeRepository.findById(request.newEmployeeId())
                    .orElseThrow(() -> new EntityNotFoundException("Nuevo empleado no encontrado"));
            workHour.setEmployee(newEmployee);
        }

        // Actualizar fecha y hora real de ejecución
        if (request.newDate() != null) {
            workHour.setDate(request.newDate());
        }

        if (request.newHour() != null) {
            workHour.setHour(request.newHour());
        }

        // Observación (nota)
        if (request.observation() != null && !request.observation().isBlank()) {
            workHour.setNote(request.observation());
        }

        // Confirmar hora
        workHour.setStatus(WorkHourStatus.CONFIRMED);
        workHour.setConfirmedDateTime(LocalDateTime.now());

        workHourRepository.save(workHour);
    }
}
