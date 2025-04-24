package com.coblan.hiring.controller;

import com.coblan.config.exception.ErrorResponseDTO;
import com.coblan.hiring.dto.AddExtraWorkHourRequestDTO;
import com.coblan.hiring.dto.ConfirmWorkHourRequestDTO;
import com.coblan.hiring.dto.WorkHourResponseDTO;
import com.coblan.hiring.usecase.AddExtraWorkHourUseCase;
import com.coblan.hiring.usecase.ConfirmWorkHourUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/work-hours")
@RequiredArgsConstructor
@Tag(name = "Horas de Trabajo", description = "Operaciones relacionadas con la horas laborables de los servicios contratados")

public class WorkHourController {

    private final ConfirmWorkHourUseCase confirmWorkHourUseCase;
    private final AddExtraWorkHourUseCase addExtraWorkHourUseCase;

    @Operation(summary = "Confirmar una hora trabajada")
    @ApiResponse(responseCode = "200", description = "Hora confirmada correctamente")
    @PutMapping("/confirm")
    public ResponseEntity<Void> confirmWorkHour(@RequestBody @Valid ConfirmWorkHourRequestDTO request) {
        confirmWorkHourUseCase.execute(request);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/extra-hours")
    @Operation(summary = "Agregar una hora extra a una contratación existente")
    @ApiResponse(responseCode = "201", description = "Hora extra agregada exitosamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = WorkHourResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Datos inválidos o contratación/empleado no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    public ResponseEntity<WorkHourResponseDTO> addExtraHour(
            @Parameter(description = "Datos necesarios para registrar una hora extra")
            @Valid @RequestBody AddExtraWorkHourRequestDTO requestDTO) {

        WorkHourResponseDTO response = addExtraWorkHourUseCase.execute(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
