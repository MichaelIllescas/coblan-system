package com.coblan.auth.exceptions;

/**
 * Excepción personalizada que se lanza cuando el usuario
 * proporciona credenciales inválidas durante el inicio de sesión.
 */
public class InvalidCredentialsException extends RuntimeException {

    /**
     * Crea una nueva instancia de {@code InvalidCredentialsException} con el mensaje especificado.
     *
     * @param message mensaje que describe el motivo de la excepción.
     */
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
