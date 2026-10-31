package com.coblan.customers.service;


import com.coblan.customers.dto.CustomerResponseDTO;
import com.coblan.customers.mapper.CustomerMapper;
import com.coblan.customers.repository.CustomerRepository;
import com.coblan.customers.usecase.GetAllCustomersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllCustomersUseCaseImpl implements GetAllCustomersUseCase {

    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerResponseDTO> execute() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::toDTO)
                .toList();
    }
}
