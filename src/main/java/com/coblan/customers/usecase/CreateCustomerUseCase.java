package com.coblan.customers.usecase;

import com.coblan.customers.dto.CustomerRequestDTO;
import com.coblan.customers.dto.CustomerResponseDTO;

public interface CreateCustomerUseCase {
    CustomerResponseDTO execute(CustomerRequestDTO request);
}
