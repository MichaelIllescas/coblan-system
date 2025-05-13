package com.coblan.employees.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no debe tener más de 50 caracteres")
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 50, message = "El apellido no debe tener más de 50 caracteres")
    private String lastName;

    @NotBlank(message = "El DNI es obligatorio")
    @Size(min = 7, max = 10, message = "El DNI debe tener entre 7 y 10 caracteres")
    @Column(unique = true)
    private String documentNumber;

    @Email(message = "El correo electrónico debe ser válido")
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "El número de teléfono es obligatorio")
    @Size(max = 20, message = "El número de teléfono no debe tener más de 20 caracteres")
    private String phone;

    @Size(max = 20, message = "El CUIT no debe tener más de 20 caracteres")
    @Column(unique = true)
    private String cuit;

    @Size(max = 100, message = "La dirección no debe tener más de 100 caracteres")
    private String address;

    @Builder.Default
    private Boolean active = true;
}
