package com.coblan.users.dto;


import com.coblan.users.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * DTO utilizado para registrar un nuevo usuario.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;

    @NotBlank(message = "El número de documento es obligatorio")
    private String documentNumber;

    @NotBlank(message = "El teléfono es obligatorio")
    private String phone;

    @NotBlank(message = "La dirección es obligatoria")
    private String address;

    @Email(message = "Debe ingresar un email válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    private Role role;

}
