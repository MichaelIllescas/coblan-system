package com.coblan.company.dto;

import com.coblan.company.domain.IvaCondition;

public record CompanyResponseDTO(
        Long id,
        String businessName,
        String cuit,
        IvaCondition ivaCondition,
        String fiscalAddress,
        String tradeName,
        String email,
        String phone
) {}
