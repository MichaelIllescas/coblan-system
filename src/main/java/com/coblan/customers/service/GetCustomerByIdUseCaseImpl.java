package com.coblan.customers.service;

import com.coblan.customers.domain.Customer;
import com.coblan.customers.dto.CustomerResponseDTO;
import com.coblan.customers.exception.CustomerNotFoundException;
import com.coblan.customers.mapper.CustomerMapper;
import com.coblan.customers.repository.CustomerRepository;
import com.coblan.customers.usecase.GetCustomerByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCustomerByIdUseCaseImpl implements GetCustomerByIdUseCase {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponseDTO execute(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente no encontrado con ID: " + id));

        return CustomerMapper.toDTO(customer);
    }
}
