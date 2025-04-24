package com.coblan.hiring.controller;


import com.coblan.config.exception.ErrorResponseDTO;
import com.coblan.hiring.dto.HiringRequestDTO;
import com.coblan.hiring.dto.HiringResponseDTO;
import com.coblan.hiring.dto.WorkHourResponseDTO;
import com.coblan.hiring.usecase.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hirings")
@RequiredArgsConstructor
@Tag(name = "Contrataciones", description = "Operaciones relacionadas con la contratación de servicios")
public class HiringController {

    private final RegisterHiringUseCase registerHiringUseCase;
    private final GetAllHiringsUseCase getAllHiringsUseCase;
    private final GetHiringsByStatusUseCase getHiringsByStatusUseCase;
    private final CancelHiringUseCase cancelHiringUseCase;
    private final GetWorkHoursByHiringUseCase getWorkHoursByHiringUseCase;



    @PostMapping
    @Operation(summary = "Registrar una nueva contratación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contratación registrada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o incompletos"),
            @ApiResponse(responseCode = "404", description = "Cliente, servicio o empleado no encontrado")
    })
    public ResponseEntity<HiringResponseDTO> registerHiring(@RequestBody @Valid HiringRequestDTO dto) {
        HiringResponseDTO response = registerHiringUseCase.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    @Operation(
            summary = "Obtener contrataciones",
            description = "Devuelve todas las contrataciones o filtra por estado si se pasa el parámetro `status`. Estados válidos: ACTIVE, CANCELLED, RENEWED."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contrataciones obtenidas correctamente"),
            @ApiResponse(responseCode = "400", description = "El parámetro 'status' es inválido",  content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class))),

            @ApiResponse(responseCode = "500", description = "Error interno del servidor",  content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    public ResponseEntity<List<HiringResponseDTO>> getAll(
            @Parameter(description = "Estado de contratación a filtrar (opcional). Valores válidos: ACTIVE, CANCELLED, RENEWED")
            @RequestParam(required = false) String status
    ) {
        try {
            if (status == null) {
                return ResponseEntity.ok(getAllHiringsUseCase.execute());
            }
            return ResponseEntity.ok(getHiringsByStatusUseCase.execute(status));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // si el status no coincide con el enum
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build(); // para cualquier otro error inesperado
        }
    }



    @PatchMapping("/{id}/cancel")
    @Operation(
            summary = "Cancelar una contratación",
            description = "Cancela una contratación vigente a solicitud del cliente. Se puede incluir un motivo de cancelación (opcional)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contratación cancelada correctamente"),
            @ApiResponse(responseCode = "400", description = "La contratación ya estaba cancelada o no existe",  content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",  content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    public ResponseEntity<Void> cancelHiring(
            @Parameter(description = "ID de la contratación a cancelar", example = "1")
            @PathVariable Long id,

            @Parameter(description = "Motivo opcional de la cancelación", example = "El cliente solicitó finalizar el servicio")
            @RequestParam(required = false) String reason
    ) {
        try {
            cancelHiringUseCase.execute(id, reason);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @Operation(summary = "Obtener todas las horas de una contratación")
    @ApiResponse(responseCode = "200", description = "Listado de horas obtenido correctamente")
    @GetMapping("/{hiringId}/work-hours")
    public ResponseEntity<List<WorkHourResponseDTO>> getWorkHoursByHiring(@PathVariable Long hiringId) {
        return ResponseEntity.ok(getWorkHoursByHiringUseCase.execute(hiringId));
    }



}
