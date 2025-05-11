package com.coblan.company.dto;

import com.coblan.company.domain.IvaCondition;
import jakarta.validation.constraints.*;

public record CompanyRequestDTO(
        @NotBlank(message = "La razón social es obligatoria")
        @Size(max = 100, message = "La razón social no debe superar los 100 caracteres")
        String businessName,

        @NotBlank(message = "El CUIT es obligatorio")
        @Pattern(regexp = "\\d{11}", message = "El CUIT debe tener 11 dígitos numéricos")
        String cuit,

        @NotNull(message = "La condición frente al IVA es obligatoria")
        IvaCondition ivaCondition,

        @NotBlank(message = "El domicilio fiscal es obligatorio")
        @Size(max = 200, message = "El domicilio fiscal no debe superar los 200 caracteres")
        String fiscalAddress,

        @Size(max = 100, message = "El nombre de fantasía no debe superar los 100 caracteres")
        String tradeName,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email debe tener un formato válido")
        String email,

        @NotBlank(message = "El teléfono es obligatorio")
        @Pattern(regexp = "\\d{10,15}", message = "El teléfono debe tener entre 10 y 15 dígitos numéricos")
        String phone
) {}
