package com.coblan.customers.service;


import com.coblan.customers.exception.CustomerInUseException;
import com.coblan.customers.exception.CustomerNotFoundException;
import com.coblan.customers.repository.CustomerRepository;
import com.coblan.customers.usecase.DeleteCustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCustomerUseCaseImpl implements DeleteCustomerUseCase {

    private final CustomerRepository customerRepository;

    @Override
    public void execute(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("Cliente no encontrado con ID: " + id);
        }

        try {
            customerRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new CustomerInUseException("No se puede eliminar el cliente porque está asociado a otros registros.");
        }
    }
}