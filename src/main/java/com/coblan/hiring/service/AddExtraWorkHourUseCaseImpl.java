package com.coblan.hiring.service;


import com.coblan.employees.domain.Employee;
import com.coblan.employees.repository.EmployeeRepository;
import com.coblan.hiring.domain.*;
import com.coblan.hiring.dto.AddExtraWorkHourRequestDTO;
import com.coblan.hiring.dto.WorkHourResponseDTO;


import com.coblan.hiring.mapper.WorkHourMapper;
import com.coblan.hiring.repository.HiringRepository;
import com.coblan.hiring.repository.WorkHourRepository;
import com.coblan.hiring.usecase.AddExtraWorkHourUseCase;
import com.coblan.hiring.usecase.WorkHourValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddExtraWorkHourUseCaseImpl implements AddExtraWorkHourUseCase {

    private final HiringRepository hiringRepository;
    private final EmployeeRepository employeeRepository;
    private final WorkHourRepository workHourRepository;
    private final WorkHourValidator workHourValidator;


    @Override
    public WorkHourResponseDTO execute(AddExtraWorkHourRequestDTO requestDTO) {
        Hiring hiring = hiringRepository.findById(requestDTO.hiringId())
                .orElseThrow(() -> new IllegalArgumentException("Contratación no encontrada"));


        Employee employee = null;
        if (requestDTO.employeeId() != null) {
            employee = employeeRepository.findById(requestDTO.employeeId())
                    .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));
        }

        WorkHour extraHour = WorkHour.builder()
                .hiring(hiring)
                .employee(employee)
                .date(requestDTO.date())
                .hour(requestDTO.time())
                .duration(60)
                .type(WorkHourType.EXTRA)
                .price(requestDTO.extraHourPrice())
                .status(WorkHourStatus.PENDING)
                .build();

        if (employee != null) {
            workHourValidator.validateEmployeeAvailabilityForExtraHour(employee.getId(), requestDTO.date(), requestDTO.time());
        }

        WorkHour saved = workHourRepository.save(extraHour);
        hiring.setServicePriceAtHiring(hiring.getServicePriceAtHiring() + requestDTO.extraHourPrice());
        hiringRepository.save(hiring);

        return WorkHourMapper.toDTO(saved);
    }
}