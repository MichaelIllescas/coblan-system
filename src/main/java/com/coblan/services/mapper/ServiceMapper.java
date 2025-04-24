package com.coblan.services.mapper;


import com.coblan.services.domain.Service;
import com.coblan.services.dto.ServiceRequestDTO;
import com.coblan.services.dto.ServiceResponseDTO;

public class ServiceMapper {

    public static Service toEntity(ServiceRequestDTO dto) {
        return Service.builder()
                .name(dto.name())
                .monthlyHours(dto.monthlyHours())
                .totalAmount(dto.totalAmount())
                .note(dto.note())
                .build();
    }

    public static ServiceResponseDTO toDTO(Service service) {
        return new ServiceResponseDTO(
                service.getId(),
                service.getName(),
                service.getMonthlyHours(),
                service.getTotalAmount(),
                service.getNote()
        );
    }
}
