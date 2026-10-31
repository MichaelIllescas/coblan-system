package com.coblan.customers.service;


import com.coblan.customers.domain.Customer;
import com.coblan.customers.dto.CustomerRequestDTO;
import com.coblan.customers.dto.CustomerResponseDTO;
import com.coblan.customers.mapper.CustomerMapper;
import com.coblan.customers.repository.CustomerRepository;
import com.coblan.customers.usecase.CreateCustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponseDTO execute(CustomerRequestDTO request) {
        Customer saved = customerRepository.save(CustomerMapper.toEntity(request));
        return CustomerMapper.toDTO(saved);
    }
}
