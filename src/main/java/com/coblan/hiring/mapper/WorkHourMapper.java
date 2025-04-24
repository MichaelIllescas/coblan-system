package com.coblan.hiring.mapper;

import com.coblan.hiring.domain.WorkHour;
import com.coblan.hiring.dto.WorkHourRequestDTO;
import com.coblan.hiring.dto.WorkHourResponseDTO;

public class WorkHourMapper {

    public static WorkHourResponseDTO toDTO(WorkHour hour) {
        return new WorkHourResponseDTO(
                hour.getId(),
                hour.getDate(),
                hour.getHour(),
                hour.getDuration(),
                hour.getType().name(),    // STANDARD / EXTRA
                hour.getStatus().name(),  // PENDING / CONFIRMED / CANCELLED
                hour.getEmployee().getFirstName() + " " +hour.getEmployee().getLastName(),
                hour.getEmployee().getId(),
                hour.getNote()
        );
    }

    public static WorkHour toEntity(WorkHourRequestDTO dto) {
        return WorkHour.builder()
                .price(dto.price())
                .status(dto.status())
                .note(dto.note())
                .build();
    }
}
