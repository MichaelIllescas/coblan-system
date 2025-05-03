package com.coblan.employees.mappers;


import com.coblan.employees.domain.Employee;
import com.coblan.employees.dto.EmployeeRequestDTO;
import com.coblan.employees.dto.EmployeeResponseDTO;

public class EmployeeMapper {

    // Convierte de RequestDTO a entidad
    public static Employee toEntity(EmployeeRequestDTO dto) {
        return Employee.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .documentNumber(dto.documentNumber())
                .email(dto.email())
                .phone(dto.phone())
                .cuit(dto.cuit())
                .address(dto.address())
                .active(true)

                .build();
    }

    // Convierte de entidad a ResponseDTO
    public static EmployeeResponseDTO toDTO(Employee employee) {
        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDocumentNumber(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getCuit(),
                employee.getAddress(),
                employee.getActive()
        );
    }

    // Actualiza una entidad existente con datos del DTO
    public static void updateEntity(Employee employee, EmployeeRequestDTO dto) {
        employee.setFirstName(dto.firstName());
        employee.setLastName(dto.lastName());
        employee.setDocumentNumber(dto.documentNumber());
        employee.setEmail(dto.email());
        employee.setPhone(dto.phone());
        employee.setCuit(dto.cuit());
        employee.setAddress(dto.address());
        employee.setActive((dto.active() != null ? dto.active() : true));

    }
}
