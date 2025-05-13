package com.coblan.customers.mapper;

import com.coblan.customers.domain.Customer;
import com.coblan.customers.dto.CustomerRequestDTO;
import com.coblan.customers.dto.CustomerResponseDTO;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequestDTO dto) {
        return Customer.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .documentNumber(dto.documentNumber())
                .phone(dto.phone())
                .email(dto.email())
                .active(dto.active())
                .address(dto.address())
                .build();
    }

    public static CustomerResponseDTO toDTO(Customer customer) {
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getDocumentNumber(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress(),
                customer.isActive()

        );
    }
}
