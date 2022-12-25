package com.example.crud.model.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenResponse {
    private String accesToken;
    private String refreshToken;
    private String typeToken = "Bearer";

    public TokenResponse(String accesToken, String requestToken) {
        this.accesToken = accesToken;
        this.refreshToken = requestToken;
    }
}
