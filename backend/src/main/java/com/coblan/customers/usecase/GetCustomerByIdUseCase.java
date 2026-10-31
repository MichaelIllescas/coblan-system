package com.coblan.customers.usecase;

import com.coblan.customers.dto.CustomerResponseDTO;

public interface GetCustomerByIdUseCase {
    CustomerResponseDTO execute(Long id);
}
