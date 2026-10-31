package com.coblan.users.service;

import com.coblan.users.domain.User;
import com.coblan.users.dto.CreateUserRequest;
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
                .documentNumber((user.getDocumentNumber()))
                .phone(user.getPhone())
                .address(user.getAddress())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .enabled(user.isEnabled())
                .role(user.getRole())
                .registrationDate(user.getRegistrationDate())
                .build();
    }


    public User toUser(UserDTO userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .documentNumber((userDto.getDocumentNumber()))
                .phone(userDto.getPhone())
                .address(userDto.getAddress())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .enabled(userDto.getEnabled())
                .role(userDto.getRole())
                .build();
    }

    public User toUserCreate(CreateUserRequest userDto) {
        return User.builder()

                .email(userDto.getEmail())
                .documentNumber((userDto.getDocumentNumber()))
                .phone(userDto.getPhone())
                .address(userDto.getAddress())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .build();
    }



}
