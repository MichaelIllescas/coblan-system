package com.coblan.users.service;


import com.coblan.users.domain.User;
import com.coblan.users.dto.ChangePasswordRequest;
import com.coblan.users.repository.UserRepository;
import com.coblan.users.usecase.ChangePasswordUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangePasswordUseCaseImpl implements ChangePasswordUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void execute(Long userId, ChangePasswordRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("La contraseña actual es incorrecta");
        }

        if (!request.getNewPassword().equals(request.getRepeatPassword())) {
            throw new RuntimeException("Las nuevas contraseñas no coinciden");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}
