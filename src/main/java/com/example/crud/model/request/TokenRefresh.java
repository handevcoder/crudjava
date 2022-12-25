package com.example.crud.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class TokenRefresh {
    @NotBlank
    private String refreshToken;
}
