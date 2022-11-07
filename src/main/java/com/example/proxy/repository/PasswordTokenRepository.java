package com.example.proxy.repository;

import com.example.proxy.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    @Query(value = "SELECT p FROM PasswordResetToken p WHERE p.token= :token")
    PasswordResetToken findByToken(String token);

}
