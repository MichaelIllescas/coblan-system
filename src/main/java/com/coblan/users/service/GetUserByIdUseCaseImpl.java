package com.coblan.users.service;

import com.coblan.users.dto.UserDTO;
import com.coblan.users.repository.UserRepository;
import com.coblan.users.usecase.GetUserByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserByIdUseCaseImpl implements GetUserByIdUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO execute(Long user_id) {
       return userMapper.toDTO(userRepository.getById(user_id));
    }
}
