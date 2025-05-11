package com.coblan.company.controller;

import com.coblan.company.dto.CompanyRequestDTO;
import com.coblan.company.dto.CompanyResponseDTO;
import com.coblan.company.usecase.GetCompanyUseCase;
import com.coblan.company.usecase.RegisterCompanyUseCase;
import com.coblan.company.usecase.UpdateCompanyUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
@Tag(name = "Compañía", description = "Operaciones relacionadas con la empresa del usuario")
public class CompanyController {

    private final RegisterCompanyUseCase registerCompanyUseCase;
    private final UpdateCompanyUseCase updateCompanyUseCase;
    private final GetCompanyUseCase getCompanyUseCase;

    @PostMapping
    @Operation(summary = "Registrar empresa", description = "Registra una nueva empresa para el usuario autenticado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa registrada correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "409", description = "El usuario ya tiene una empresa registrada")
    })
    public ResponseEntity<CompanyResponseDTO> registerCompany(
            @RequestBody @Valid CompanyRequestDTO dto,
            @RequestAttribute("userId") Long userId
    ) {
        CompanyResponseDTO response = registerCompanyUseCase.execute(userId, dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Operation(summary = "Actualizar empresa", description = "Actualiza los datos de la empresa del usuario autenticado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada")
    })
    public ResponseEntity<CompanyResponseDTO> updateCompany(
            @RequestBody @Valid CompanyRequestDTO   dto,
            @RequestAttribute("userId") Long userId
    ) {
        CompanyResponseDTO response = updateCompanyUseCase.execute(userId, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Obtener empresa", description = "Obtiene los datos de la empresa del usuario autenticado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Datos de la empresa obtenidos correctamente"),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada")
    })
    public ResponseEntity<CompanyResponseDTO> getCompany(@RequestAttribute("userId") Long userId) {
        CompanyResponseDTO response = getCompanyUseCase.execute(userId);
        return ResponseEntity.ok(response);
    }
}
