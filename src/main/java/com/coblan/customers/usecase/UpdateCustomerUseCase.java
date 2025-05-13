package com.coblan.customers.usecase;

import com.coblan.customers.dto.CustomerRequestDTO;
import com.coblan.customers.dto.CustomerResponseDTO;

public interface UpdateCustomerUseCase {
    CustomerResponseDTO execute(Long id, CustomerRequestDTO request);
}
