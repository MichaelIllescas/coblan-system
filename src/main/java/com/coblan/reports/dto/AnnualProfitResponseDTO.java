package com.coblan.reports.dto;

import java.util.Map;

/**
 * DTO que representa las ganancias anuales,
 * con el nombre del mes como clave y el monto como valor.
 */
public record AnnualProfitResponseDTO(Map<String, Double> monthlyProfits) {
}