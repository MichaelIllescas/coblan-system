package com.coblan.auth.repository;

import com.coblan.auth.entity.PasswordResetToken;
import com.coblan.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
    void deleteByUser(User user);
    Optional<PasswordResetToken> findByUser(User user);

}
