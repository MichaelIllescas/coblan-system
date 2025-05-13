package com.coblan.customers.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "customers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no debe superar los 50 caracteres")
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 50, message = "El apellido no debe superar los 50 caracteres")
    private String lastName;

    @NotBlank(message = "El número de documento es obligatorio")
    @Pattern(regexp = "\\d{7,15}", message = "El número de documento debe tener entre 7 y 15 dígitos")
    private String documentNumber;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(max = 20, message = "El número de teléfono no debe superar los 20 caracteres")
    private String phone;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico debe ser válido")
    private String email;

    @NotBlank(message = "El la dirección  es obligatoria")
    private String address;


    private boolean active;
}
