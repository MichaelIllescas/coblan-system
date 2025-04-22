package com.coblan.employees.controller;


import com.coblan.employees.dto.EmployeeRequestDTO;
import com.coblan.employees.dto.EmployeeResponseDTO;
import com.coblan.employees.usecase.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Tag(name = "Empleados", description = "Operaciones relacionadas con los empleados registrados en el sistema")
public class EmployeeController {

    private final CreateEmployeeUseCase createEmployeeUseCase;
    private final GetAllEmployeesUseCase getAllEmployeesUseCase;
    private final GetEmployeeByIdUseCase getEmployeeByIdUseCase;
    private final UpdateEmployeeUseCase updateEmployeeUseCase;
    private final ToggleEmployeeStatusUseCase toggleEmployeeStatusUseCase;

    @PostMapping("/create")
    @Operation(summary = "Registrar un nuevo empleado")
    @ApiResponse(responseCode = "200", description = "Empleado creado exitosamente")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(
            @Valid @RequestBody EmployeeRequestDTO requestDTO) {
        return ResponseEntity.ok(createEmployeeUseCase.execute(requestDTO));
    }

    @GetMapping("/getAll")
    @Operation(summary = "Obtener todos los empleados", description = "Permite obtener todos los empleados o solo los activos si se indica el parámetro")
    @ApiResponse(responseCode = "200", description = "Lista de empleados obtenida correctamente")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees(
            @Parameter(description = "Si se establece en true, filtra solo empleados activos")
            @RequestParam(value = "active", required = false) Boolean onlyActive) {
        return ResponseEntity.ok(getAllEmployeesUseCase.execute(onlyActive));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un empleado por ID")
    @ApiResponse(responseCode = "200", description = "Empleado obtenido exitosamente")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(
            @Parameter(description = "ID del empleado") @PathVariable Long id) {
        return ResponseEntity.ok(getEmployeeByIdUseCase.execute(id));
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Actualizar un empleado")
    @ApiResponse(responseCode = "200", description = "Empleado actualizado exitosamente")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @Parameter(description = "ID del empleado a actualizar") @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDTO requestDTO) {
        return ResponseEntity.ok(updateEmployeeUseCase.execute(id, requestDTO));
    }

    @PatchMapping("/toggle-status/{id}")
    @Operation(summary = "Activar o desactivar un empleado")
    @ApiResponse(responseCode = "204", description = "Estado del empleado actualizado correctamente")
    public ResponseEntity<Void> toggleEmployeeStatus(
            @Parameter(description = "ID del empleado") @PathVariable Long id) {
        toggleEmployeeStatusUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}