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
    @Operation(
            summary = "Registrar empresa",
            description = "Registra la empresa única del sistema. Solo puede existir una empresa registrada."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa registrada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en el cuerpo de la solicitud"),
            @ApiResponse(responseCode = "409", description = "Ya existe una empresa registrada en el sistema")
    })
    public ResponseEntity<CompanyResponseDTO> registerCompany(
            @RequestBody @Valid CompanyRequestDTO dto
    ) {
        CompanyResponseDTO response = registerCompanyUseCase.execute(dto);
        return ResponseEntity.ok(response);
    }


    @PutMapping
    @Operation(summary = "Actualizar empresa", description = "Actualiza los datos de la empresa registrada en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada")
    })
    public ResponseEntity<CompanyResponseDTO> updateCompany(
            @RequestBody @Valid CompanyRequestDTO   dto
    ) {
        CompanyResponseDTO response = updateCompanyUseCase.execute(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Obtener empresa", description = "Obtiene los datos de la empresa registrada en sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Datos de la empresa obtenidos correctamente"),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada")
    })
    public ResponseEntity<CompanyResponseDTO> getCompany() {
        CompanyResponseDTO response = getCompanyUseCase.execute();
        return ResponseEntity.ok(response);
    }
}
