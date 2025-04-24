package com.coblan.hiring.mapper;

import com.coblan.customers.domain.Customer;
import com.coblan.employees.domain.Employee;
import com.coblan.hiring.domain.Hiring;
import com.coblan.hiring.domain.HiringStatus;
import com.coblan.hiring.domain.ScheduleEntry;
import com.coblan.hiring.dto.HiringRequestDTO;
import com.coblan.hiring.dto.HiringResponseDTO;
import com.coblan.hiring.dto.ScheduleEntryDTO;
import com.coblan.services.domain.Service;

import java.util.Set;
import java.util.stream.Collectors;

public class HiringMapper {

    public static Hiring toEntity(HiringRequestDTO dto, Customer customer, Service service, Employee employee) {
        Set<ScheduleEntry> schedule = dto.schedule().stream()
                .map(entry -> ScheduleEntry.builder()
                        .day(entry.day())
                        .hour(entry.hour())
                        .build())
                .collect(Collectors.toSet());

        return Hiring.builder()
                .startDate(dto.startDate())
                .schedule(schedule)
                .customer(customer)
                .service(service)
                .employee(employee)
                .status(HiringStatus.ACTIVE)
                .build();
    }


    public static HiringResponseDTO toResponseDTO(Hiring hiring) {
        Set<ScheduleEntryDTO> schedule = hiring.getSchedule().stream()
                .map(HiringMapper::toScheduleEntryDTO)
                .collect(Collectors.toSet());

        return new HiringResponseDTO(
                hiring.getId(),
                hiring.getStartDate(),
                schedule,
                hiring.getStatus().name(),
                hiring.getCancellationReason(),
                hiring.getCustomer().getId(),
                hiring.getCustomer().getFirstName(),
                hiring.getService().getId(),
                hiring.getService().getName(),
                hiring.getEmployee().getId(),
                hiring.getEmployee().getFirstName() + " " + hiring.getEmployee().getLastName(),
                hiring.getServicePriceAtHiring()

        );
    }

    private static ScheduleEntryDTO toScheduleEntryDTO(ScheduleEntry entry) {
        return new ScheduleEntryDTO(entry.getDay(), entry.getHour());
    }
}
