package com.coblan.customers.controller;

import com.coblan.customers.dto.CustomerRequestDTO;
import com.coblan.customers.dto.CustomerResponseDTO;
import com.coblan.customers.usecase.*;
import com.coblan.config.exception.ErrorResponseDTO;
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

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Operaciones para gestionar clientes")
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetAllCustomersUseCase getAllCustomersUseCase;
    private final GetCustomerByIdUseCase getCustomerByIdUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;

    @Operation(summary = "Registrar un nuevo cliente")
    @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Error de validación",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(
            @Valid @RequestBody CustomerRequestDTO request) {
        CustomerResponseDTO response = createCustomerUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Obtener todos los clientes")
    @ApiResponse(responseCode = "200", description = "Listado de clientes recuperado correctamente")
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        return ResponseEntity.ok(getAllCustomersUseCase.execute());
    }

    @Operation(summary = "Buscar un cliente por su ID")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(
            @Parameter(description = "ID del cliente") @PathVariable Long id) {
        return ResponseEntity.ok(getCustomerByIdUseCase.execute(id));
    }

    @Operation(summary = "Actualizar un cliente existente")
    @ApiResponse(responseCode = "200", description = "Cliente actualizado correctamente")
    @ApiResponse(responseCode = "400", description = "Error de validación",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(
            @Parameter(description = "ID del cliente") @PathVariable Long id,
            @Valid @RequestBody CustomerRequestDTO request) {
        return ResponseEntity.ok(updateCustomerUseCase.execute(id, request));
    }

    @Operation(summary = "Eliminar un cliente")
    @ApiResponse(responseCode = "204", description = "Cliente eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    @ApiResponse(responseCode = "409", description = "El cliente está asociado a otros registros",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(
            @Parameter(description = "ID del cliente") @PathVariable Long id) {
        deleteCustomerUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
