package com.apekking.erpsystem.security.auth.dto;

import java.util.List;

public class LoginResponse {

    public String accessToken;
    public String tokenType = "Bearer";
    public long expiresIn;
    public UserInfo user;

    public static class UserInfo {
        public Long id;
        public String username;
        public String email;
        public Long companyId;
        public Long branchId;
        public List<String> roles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}

