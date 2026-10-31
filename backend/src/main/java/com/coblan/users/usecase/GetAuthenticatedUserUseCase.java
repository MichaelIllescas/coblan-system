package com.coblan.users.usecase;

import com.coblan.users.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface GetAuthenticatedUserUseCase {
    UserDTO execute(HttpServletRequest request);
}
