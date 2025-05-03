package com.coblan.users.service;

import com.coblan.users.domain.Role;
import com.coblan.users.domain.User;
import com.coblan.users.repository.UserRepository;
import com.coblan.users.usecase.InsertAdminUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class InsertAdminUserUseCaseImpl implements InsertAdminUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void execute() {
        Optional<User> existingUser = userRepository.findByEmail("admin@admin.com");

        if (existingUser.isEmpty()) {
            User admin = User.builder()
                    .firstName("Admin")
                    .lastName("Sistema")
                    .documentNumber("00000000")
                    .phone("0000000000")
                    .address("Sin direccion")
                    .email("admin@admin.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .enabled(true)
                    .build();

            userRepository.save(admin);
        }
    }
}
