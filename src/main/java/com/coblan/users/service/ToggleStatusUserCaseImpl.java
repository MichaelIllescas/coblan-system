package com.coblan.users.service;

import com.coblan.users.domain.User;
import com.coblan.users.dto.UserDTO;
import com.coblan.users.repository.UserRepository;
import com.coblan.users.usecase.ToggleStatusUserCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class ToggleStatusUserCaseImpl implements ToggleStatusUserCase {

    private final UserRepository userRepository;


    @Override
    public void execute(Long id) {
        User user= userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("El usuario que desea actualizar, no fue encontrado en el sistema"));
        if (user.isEnabled()){
            user.setEnabled(false);
        }else {
            user.setEnabled(true);
        }
        userRepository.save(user);
    }
}
