package com.coblan.reports.controller;

import com.coblan.reports.dto.AnnualProfitResponseDTO;
import com.coblan.reports.dto.DashboardStatsDTO;
import com.coblan.reports.dto.FinancialReportDTO;
import com.coblan.reports.dto.HiringReportDTO;
import com.coblan.reports.usecase.DashboardService;
import com.coblan.reports.usecase.GetAnnualFinancialReportUseCase;
import com.coblan.reports.usecase.GetMonthlyFinancialReportUseCase;
import com.coblan.reports.usecase.GetMonthlyHiringsReportUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
@Tag(name = "Reportes", description = "Reportes financieros de las operaciones de la empresa")

public class ReportsController {

    private final GetMonthlyHiringsReportUseCase getMonthlyHiringsReportUseCase;
    private final GetMonthlyFinancialReportUseCase getMonthlyFinancialReportUseCase;
    private final GetAnnualFinancialReportUseCase getAnnualFinancialReportUseCase;
    private final DashboardService dashboardService;


    @GetMapping("/hirings")
    @Operation(summary = "Obtener reporte mensual de contrataciones")
    @ApiResponse(responseCode = "200", description = "Reporte obtenido correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = HiringReportDTO.class)))
    public ResponseEntity<List<HiringReportDTO>> getMonthlyHiringsReport(
            @RequestParam int month,
            @RequestParam int year
    ) {
        List<HiringReportDTO> report = getMonthlyHiringsReportUseCase.execute( year, month);
        return ResponseEntity.ok(report);
    }




    @GetMapping("/monthly")
    @Operation(summary = "Genera el reporte financiero mensual")
    public ResponseEntity<FinancialReportDTO> getMonthlyReport(
            @RequestParam int month,
            @RequestParam int year) {

        FinancialReportDTO report = getMonthlyFinancialReportUseCase.execute( year, month);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/annual")
    @Operation(summary = "Genera el reporte financiero anual")
    public ResponseEntity<AnnualProfitResponseDTO> getAnnualReport(
            @RequestParam int year) {

        AnnualProfitResponseDTO report = getAnnualFinancialReportUseCase.execute(year);
        return ResponseEntity.ok(report);
    }


    @GetMapping("/dashboard/stats")
    @Operation(summary = "Genera el reporte de cantidad de empleados, de clientes, de contrataciones, y de servicios ")
    public ResponseEntity<DashboardStatsDTO> getDashboardStats() {
        return ResponseEntity.ok(dashboardService.getStats());
    }


}
