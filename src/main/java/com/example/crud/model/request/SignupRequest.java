package com.example.crud.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
public class SignupRequest {
    @NotBlank(message = "harus punya nama")
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private Set<String> role;
    @NotBlank
    private String password;
}