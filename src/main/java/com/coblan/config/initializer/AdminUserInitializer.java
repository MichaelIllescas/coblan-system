package com.coblan.config.initializer;

import com.coblan.users.usecase.InsertAdminUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminUserInitializer implements ApplicationRunner {

    private final InsertAdminUserUseCase insertAdminUserUseCase;

    @Override
    public void run(ApplicationArguments args) {
        insertAdminUserUseCase.execute();
    }
}
