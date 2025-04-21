package com.coblan.users.usecase;

import com.coblan.users.dto.UserDTO;

public interface GetUserByIdUseCase {

    UserDTO execute(Long user_id);
}
