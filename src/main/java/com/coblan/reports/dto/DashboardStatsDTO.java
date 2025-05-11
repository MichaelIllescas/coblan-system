package com.coblan.reports.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardStatsDTO {
    private long customers;
    private long employees;
    private long hirings;
    private long services;
}