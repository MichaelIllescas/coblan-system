package com.coblan.users.service;

import com.coblan.auth.service.CookieService;
import com.coblan.users.domain.User;
import com.coblan.users.dto.UserDTO;
import com.coblan.users.usecase.GetAuthenticatedUserUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetAuthenticatedUserUseCaseImpl implements GetAuthenticatedUserUseCase {

    private final CookieService cookieService;
    private final UserMapper userMapper;

    @Override
    public UserDTO execute(HttpServletRequest request) {
        Optional<User> optionalUser = cookieService.getUserFromCookie(request);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Usuario no autenticado");
        }
        return userMapper.toDTO(optionalUser.get());
    }
}

