package com.coblan.hiring.impl;

import com.coblan.customers.domain.Customer;
import com.coblan.customers.repository.CustomerRepository;
import com.coblan.employees.domain.Employee;
import com.coblan.employees.repository.EmployeeRepository;
import com.coblan.hiring.domain.Hiring;
import com.coblan.hiring.domain.WorkHour;
import com.coblan.hiring.dto.HiringRequestDTO;
import com.coblan.hiring.dto.HiringResponseDTO;
import com.coblan.hiring.mapper.HiringMapper;
import com.coblan.hiring.repository.HiringRepository;
import com.coblan.hiring.usecase.HiringValidator;
import com.coblan.hiring.usecase.RegisterHiringUseCase;
import com.coblan.hiring.usecase.GenerateInitialWorkHoursUseCase;
import com.coblan.services.domain.Service;
import com.coblan.services.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;


import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class RegisterHiringService implements RegisterHiringUseCase {

    private final HiringRepository hiringRepository;
    private final CustomerRepository customerRepository;
    private final ServiceRepository serviceRepository;
    private final EmployeeRepository employeeRepository;
    private final GenerateInitialWorkHoursUseCase initialWorkHourGenerator;
    private  final HiringValidator hiringValidator;

    /**
     * Ejecuta el registro de una nueva contratación.
     * Valida entidades relacionadas, genera las horas de trabajo, guarda la contratación y devuelve el DTO de respuesta.
     */
    @Override
    public HiringResponseDTO execute(HiringRequestDTO dto) {
        // Buscar cliente, servicio y empleado por ID y lanzar error si no existen
        Customer customer = customerRepository.findById(dto.customerId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Service service = serviceRepository.findById(dto.serviceId())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        Employee employee = employeeRepository.findById(dto.employeeId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        // Mapear el DTO a entidad Hiring con estado ACTIVO
        Hiring hiring = HiringMapper.toEntity(dto, customer, service, employee);

        // Generar horas de trabajo usando la clase dedicada al caso de uso
        List<WorkHour> workHours = initialWorkHourGenerator.generate(hiring);
        workHours.forEach(wh -> wh.setHiring(hiring));
        hiring.setWorkHours(workHours);



    // se guarda el precio de la contratacion por el servicio
        hiring.setServicePriceAtHiring(service.getTotalAmount());
//valido que el empleado no se enucentre ocupado en otros servicios
        hiringValidator.validateScheduleConflict(hiring);
        // Persistir la contratación y devolver la respuesta mapeada
        Hiring saved = hiringRepository.save(hiring);
        return HiringMapper.toResponseDTO(saved);
    }
}