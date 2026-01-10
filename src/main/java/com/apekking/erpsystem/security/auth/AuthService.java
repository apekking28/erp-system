package com.apekking.erpsystem.security.auth;

import com.apekking.erpsystem.security.auth.dto.LoginRequest;
import com.apekking.erpsystem.security.auth.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}

