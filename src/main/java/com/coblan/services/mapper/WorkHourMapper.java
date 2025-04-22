package com.coblan.services.mapper;

import com.coblan.services.domain.Service;
import com.coblan.services.domain.WorkHour;
import com.coblan.services.dto.WorkHourRequestDTO;
import com.coblan.services.dto.WorkHourResponseDTO;

public class WorkHourMapper {

    public static WorkHourResponseDTO toDTO(WorkHour hour) {
        return new WorkHourResponseDTO(
                hour.getId(),
                hour.getPrice(),
                hour.getStatus(),
                hour.getNote()
        );
    }

    public static WorkHour toEntity(WorkHourRequestDTO dto, Service service) {
        return WorkHour.builder()
                .price(dto.price())
                .status(dto.status())
                .note(dto.note())
                .service(service)
                .build();
    }
}
