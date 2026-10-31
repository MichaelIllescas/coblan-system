package com.coblan.customers.exception;

public class CustomerInUseException extends RuntimeException {
    public CustomerInUseException(String message) {
        super(message);
    }
}
