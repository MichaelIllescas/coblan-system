package com.coblan.services.controller;

import com.coblan.config.exception.ErrorResponseDTO;
import com.coblan.services.dto.ServiceRequestDTO;
import com.coblan.services.dto.ServiceResponseDTO;
import com.coblan.services.usecase.CreateServiceUseCase;
import com.coblan.services.usecase.DeleteServiceUseCase;
import com.coblan.services.usecase.GetAllServicesUseCase;
import com.coblan.services.usecase.UpdateServiceUseCase;
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

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
@Tag(name = "Servicios", description = "Operaciones relacionadas con los servicios ofrecidos por la empresa")
public class ServicesController {

    private final CreateServiceUseCase createServiceUseCase;
    private final GetAllServicesUseCase getAllServicesUseCase;
    private final UpdateServiceUseCase updateServiceUseCase;
    private final DeleteServiceUseCase deleteServiceUseCase;

    @PostMapping
    @Operation(summary = "Registrar un nuevo servicio")
    @ApiResponse(responseCode = "200", description = "Servicio registrado exitosamente")
    @ApiResponse(responseCode = "400", description = "Error de validación",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    public ResponseEntity<ServiceResponseDTO> createService(
            @Valid @RequestBody ServiceRequestDTO dto) {
        ServiceResponseDTO created = createServiceUseCase.execute(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    @Operation(summary = "Listar todos los servicios")
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    public ResponseEntity<List<ServiceResponseDTO>> getAllServices() {
        return ResponseEntity.ok(getAllServicesUseCase.execute());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un servicio existente")
    @ApiResponse(responseCode = "200", description = "Servicio actualizado exitosamente")
    @ApiResponse(responseCode = "400", description = "Error de validación",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Servicio no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    public ResponseEntity<ServiceResponseDTO> updateService(
            @Parameter(description = "ID del servicio a actualizar") @PathVariable Long id,
            @Valid @RequestBody ServiceRequestDTO dto) {
        ServiceResponseDTO updated = updateServiceUseCase.execute(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un servicio por ID")
    @ApiResponse(responseCode = "204", description = "Servicio eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Servicio no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    @ApiResponse(responseCode = "409", description = "No se puede eliminar el servicio porque está en uso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    public ResponseEntity<Void> deleteService(
            @Parameter(description = "ID del servicio a eliminar") @PathVariable Long id) {
        deleteServiceUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
