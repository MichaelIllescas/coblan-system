package com.coblan.customers.service;

import com.coblan.customers.domain.Customer;
import com.coblan.customers.dto.CustomerRequestDTO;
import com.coblan.customers.dto.CustomerResponseDTO;
import com.coblan.customers.exception.CustomerNotFoundException;
import com.coblan.customers.mapper.CustomerMapper;
import com.coblan.customers.repository.CustomerRepository;
import com.coblan.customers.usecase.UpdateCustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCase {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponseDTO execute(Long id, CustomerRequestDTO request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente no encontrado con ID: " + id));

        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setDocumentNumber(request.documentNumber());
        customer.setPhone(request.phone());
        customer.setEmail(request.email());
        customer.setActive(request.active());

        Customer updated = customerRepository.save(customer);
        return CustomerMapper.toDTO(updated);
    }
}
