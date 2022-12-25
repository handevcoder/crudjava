package com.example.crud.repository;

import com.example.crud.model.TokenRequest;
import com.example.crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenRequest, Long> {
    Optional<TokenRequest> findByToken(String token);
    @Modifying
    int deleteByUser(User user);
}
