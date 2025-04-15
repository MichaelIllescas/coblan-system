package com.coblan.users.service;

import com.coblan.users.domain.User;
import com.coblan.users.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {
    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .enabled(user.isEnabled())
                .role(user.getRole())
                .registrationDate(user.getRegistrationDate())
                .build();
    }
}
