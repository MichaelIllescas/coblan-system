package com.coblan.customers.usecase;

import com.coblan.customers.dto.CustomerResponseDTO;

import java.util.List;

public interface GetAllCustomersUseCase {
    List<CustomerResponseDTO> execute();
}
