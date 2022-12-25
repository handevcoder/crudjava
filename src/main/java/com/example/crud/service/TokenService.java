package com.example.crud.service;

import com.example.crud.repository.TokenRepository;
import com.example.crud.repository.UserRepository;
import com.example.crud.exception.TokenException;
import com.example.crud.model.TokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenService {

    @Value("${hanif.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private TokenRepository tokenRepo;

    @Autowired
    private UserRepository userRepo;

    public Optional<TokenRequest> findByToken(String token) {
        return tokenRepo.findByToken(token);
    }

    public TokenRequest createRefreshToken(Long userId) {
        TokenRequest refreshToken = new TokenRequest();

        refreshToken.setUser(userRepo.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken = tokenRepo.save(refreshToken);

        return refreshToken;
    }

    public TokenRequest verifyExpiration(TokenRequest token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0){
            tokenRepo.delete(token);
            throw new TokenException(token.getToken(), "Refresh token was expired, Please make a new signin request");
        }
        return token;
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        return tokenRepo.deleteByUser(userRepo.findById(userId).get());
    }

}
