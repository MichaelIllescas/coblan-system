package com.coblan.users.service;

import com.coblan.users.domain.Role;
import com.coblan.users.domain.User;
import com.coblan.users.dto.CreateUserRequest;
import com.coblan.users.dto.UserDTO;
import com.coblan.users.repository.UserRepository;
import com.coblan.users.usecase.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    @Override
    public UserDTO execute(CreateUserRequest request) {
        // Validación: verificar si ya existe un usuario con ese email
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario registrado con el email " + request.getEmail());
        }

        User newUser = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .documentNumber(request.getDocumentNumber())
                .phone(request.getPhone())
                .address(request.getAddress())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)        // se asigna por defecto
                .enabled(true)
                .build();

        userRepository.save(newUser);

        return userMapper.toDTO(newUser);
    }
}
