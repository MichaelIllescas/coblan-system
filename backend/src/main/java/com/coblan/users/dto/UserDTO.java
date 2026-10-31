package com.coblan.users.dto;

import com.coblan.users.domain.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

/**
 * DTO utilizado para representar los datos de un usuario en el sistema.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String documentNumber;
    private String phone;
    private String address;
    private String email;
    private Boolean enabled;
    private Role role;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;


}
