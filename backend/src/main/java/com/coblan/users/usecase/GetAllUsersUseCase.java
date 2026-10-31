package com.coblan.users.usecase;

import com.coblan.users.dto.UserDTO;

import java.util.List;

public interface GetAllUsersUseCase {

    List<UserDTO> execute();
}
