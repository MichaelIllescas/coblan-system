package com.coblan.users.usecase;

import com.coblan.users.domain.User;
import com.coblan.users.dto.UserDTO;

public interface UpdateUserUseCase {

    UserDTO execute(UserDTO userDTO);
}
