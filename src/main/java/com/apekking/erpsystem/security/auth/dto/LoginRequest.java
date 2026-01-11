package com.apekking.erpsystem.security.auth.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    private String username;
    private String email;

    @NotBlank
    private String password;

    // getters & setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }
}

