package com.coblan.salaryPayments.controller;

import com.coblan.config.exception.ErrorResponseDTO;
import com.coblan.salaryPayments.dto.SalaryPaymentRequestDTO;
import com.coblan.salaryPayments.dto.SalaryPaymentResponseDTO;
import com.coblan.salaryPayments.useCase.*;
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
@RequestMapping("/salary-payments")
@RequiredArgsConstructor
@Tag(name = "Pagos de Salarios", description = "Gestión de pagos realizados a empleados")
public class SalaryPaymentController {

    private final CreateSalaryPaymentUseCase createUseCase;
    private final GetAllSalaryPaymentsUseCase getAllUseCase;
    private final GetSalaryPaymentByIdUseCase getByIdUseCase;
    private final UpdateSalaryPaymentUseCase updateUseCase;
    private final DeleteSalaryPaymentUseCase deleteUseCase;

    @PostMapping
    @Operation(summary = "Registrar un nuevo pago de salario")
    @ApiResponse(responseCode = "201", description = "Pago registrado exitosamente")
    @ApiResponse(responseCode = "400", description = "Error de validación",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Empleado no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    public ResponseEntity<SalaryPaymentResponseDTO> createPayment(
            @Valid @RequestBody SalaryPaymentRequestDTO dto) {
        SalaryPaymentResponseDTO response = createUseCase.execute(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar todos los pagos de salarios")
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    public ResponseEntity<List<SalaryPaymentResponseDTO>> getAllPayments() {
        return ResponseEntity.ok(getAllUseCase.execute());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un pago de salario por ID")
    @ApiResponse(responseCode = "200", description = "Pago obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Pago no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    public ResponseEntity<SalaryPaymentResponseDTO> getPaymentById(
            @Parameter(description = "ID del pago") @PathVariable Long id) {
        return ResponseEntity.ok(getByIdUseCase.execute(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un pago de salario existente")
    @ApiResponse(responseCode = "200", description = "Pago actualizado exitosamente")
    @ApiResponse(responseCode = "400", description = "Error de validación",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Pago o empleado no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    public ResponseEntity<SalaryPaymentResponseDTO> updatePayment(
            @Parameter(description = "ID del pago") @PathVariable Long id,
            @Valid @RequestBody SalaryPaymentRequestDTO dto) {
        return ResponseEntity.ok(updateUseCase.execute(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un pago de salario por ID")
    @ApiResponse(responseCode = "204", description = "Pago eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Pago no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    public ResponseEntity<Void> deletePayment(
            @Parameter(description = "ID del pago") @PathVariable Long id) {
        deleteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
