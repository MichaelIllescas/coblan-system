package com.coblan.users.usecase;

import com.coblan.users.dto.CreateUserRequest;
import com.coblan.users.dto.UserDTO;

public interface RegisterUserUseCase {
    UserDTO execute(CreateUserRequest request);
}
