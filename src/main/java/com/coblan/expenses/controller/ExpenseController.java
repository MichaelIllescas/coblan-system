package com.coblan.expenses.controller;

import com.coblan.config.exception.ErrorResponseDTO;
import com.coblan.expenses.dto.ExpenseRequestDTO;
import com.coblan.expenses.dto.ExpenseResponseDTO;
import com.coblan.expenses.usecase.*;
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
@RequiredArgsConstructor
@RequestMapping("/expenses")
@Tag(name = "Gastos", description = "Gestión de los gastos registrados en el sistema.")
public class ExpenseController {

    private final GetExpenseByIdUseCase getExpenseByIdUseCase;
    private final GetAllExpensesUseCase getAllExpensesUseCase;
    private final CreateExpenseUseCase createExpenseUseCase;
    private final UpdateExpenseUseCase updateExpenseUseCase;
    private final DeleteExpenseUseCase deleteExpenseUseCase;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener gasto por ID")
    @ApiResponse(responseCode = "200", description = "Gasto obtenido correctamente")
    @ApiResponse(responseCode = "404", description = "Gasto no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    public ResponseEntity<ExpenseResponseDTO> getExpenseById(@PathVariable Long id) {
        ExpenseResponseDTO expense = getExpenseByIdUseCase.execute(id);
        return ResponseEntity.ok(expense);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los gastos")
    @ApiResponse(responseCode = "200", description = "Lista de gastos obtenida correctamente")
    public ResponseEntity<List<ExpenseResponseDTO>> getAllExpenses() {
        List<ExpenseResponseDTO> expenses = getAllExpensesUseCase.execute();
        return ResponseEntity.ok(expenses);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo gasto")
    @ApiResponse(responseCode = "201", description = "Gasto creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    public ResponseEntity<ExpenseResponseDTO> createExpense(@Valid @RequestBody ExpenseRequestDTO dto) {
        ExpenseResponseDTO createdExpense = createExpenseUseCase.execute(dto);
        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar gasto existente")
    @ApiResponse(responseCode = "200", description = "Gasto actualizado correctamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Gasto no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    public ResponseEntity<ExpenseResponseDTO> updateExpense(
            @Parameter(description = "ID del gasto a actualizar") @PathVariable Long id,
            @Valid @RequestBody ExpenseRequestDTO dto) {
        ExpenseResponseDTO updatedExpense = updateExpenseUseCase.execute(id, dto);
        return ResponseEntity.ok(updatedExpense);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar gasto por ID")
    @ApiResponse(responseCode = "204", description = "Gasto eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Gasto no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
    public ResponseEntity<Void> deleteExpense(
            @Parameter(description = "ID del gasto a eliminar") @PathVariable Long id) {
        deleteExpenseUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}