package com.coblan.users.usecase;

import com.coblan.users.dto.ChangePasswordRequest;

public interface ChangePasswordUseCase {
    void execute(Long userId, ChangePasswordRequest request);
}
