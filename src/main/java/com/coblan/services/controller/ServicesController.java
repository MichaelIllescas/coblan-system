package com.coblan.services.controller;

import com.coblan.services.dto.ServiceRequestDTO;
import com.coblan.services.dto.ServiceResponseDTO;
import com.coblan.services.usecase.CreateServiceUseCase;
import com.coblan.services.usecase.DeleteServiceUseCase;
import com.coblan.services.usecase.GetAllServicesUseCase;
import com.coblan.services.usecase.UpdateServiceUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
@Tag(name = "Servicios", description = "Operaciones relacionadas con los servicios ofrecidos por la empresa")
public class ServicesController {

    private final CreateServiceUseCase createServiceUseCase;
    private final GetAllServicesUseCase getAllServicesUseCase;
    private final UpdateServiceUseCase updateServiceUseCase;
    private final DeleteServiceUseCase deleteServiceUseCase;

    @PostMapping("/createServise")
    @Operation(summary = "Registrar un nuevo servicio")
    public ResponseEntity<ServiceResponseDTO> createService(@RequestBody ServiceRequestDTO dto) {
        ServiceResponseDTO created = createServiceUseCase.execute(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/getAll")
    @Operation(summary = "Listar todos los servicios")
    public ResponseEntity<List<ServiceResponseDTO>> getAllServices() {
        List<ServiceResponseDTO> services = getAllServicesUseCase.execute();
        return ResponseEntity.ok(services);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Actualizar un servicio existente")
    public ResponseEntity<ServiceResponseDTO> updateService(@PathVariable Long id, @RequestBody ServiceRequestDTO dto) {
        ServiceResponseDTO updated = updateServiceUseCase.execute(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Eliminar un servicio por ID")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        deleteServiceUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
