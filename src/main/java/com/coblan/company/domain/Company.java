package com.coblan.company.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La razón social es obligatoria")
    @Size(max = 100, message = "La razón social no debe superar los 100 caracteres")
    @Column(name = "business_name", nullable = false)
    private String businessName;

    @NotBlank(message = "El CUIT es obligatorio")
    @Pattern(regexp = "\\d{11}", message = "El CUIT debe tener 11 dígitos numéricos")
    @Column(name = "cuit", nullable = false, unique = true, length = 11)
    private String cuit;

    @Enumerated(EnumType.STRING)
    @Column(name = "iva_condition", nullable = false, length = 30)
    @NotNull(message = "La condición frente al IVA es obligatoria")
    private IvaCondition ivaCondition;

    @NotBlank(message = "El domicilio fiscal es obligatorio")
    @Size(max = 200, message = "El domicilio fiscal no debe superar los 200 caracteres")
    @Column(name = "fiscal_address", nullable = false)
    private String fiscalAddress;

    @NotBlank(message = "El nombre de fantasía es obligatorio")
    @Size(max = 100, message = "El nombre de fantasía no debe superar los 100 caracteres")
    @Column(name = "trade_name", nullable = false)
    private String tradeName;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "\\d{10,15}", message = "El teléfono debe tener entre 10 y 15 dígitos numéricos")
    @Column(name = "phone", nullable = false)
    private String phone;

}
