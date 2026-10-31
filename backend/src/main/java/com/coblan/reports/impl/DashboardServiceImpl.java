package com.coblan.reports.impl;


import com.coblan.customers.repository.CustomerRepository;
import com.coblan.employees.repository.EmployeeRepository;
import com.coblan.hiring.repository.HiringRepository;
import com.coblan.reports.dto.DashboardStatsDTO;
import com.coblan.reports.usecase.DashboardService;
import com.coblan.services.repository.ServiceRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final HiringRepository hiringRepository;
    private final ServiceRepository serviceRepository;

    @Override
    public DashboardStatsDTO getStats() {
        long customers = customerRepository.count();
        long employees = employeeRepository.count();
        long hirings = hiringRepository.count();
        long services = serviceRepository.count();

        return new DashboardStatsDTO(customers, employees, hirings, services);
    }
}