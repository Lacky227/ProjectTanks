package com.veedev.authservice.service;

import com.veedev.authservice.dto.AuthResponse;
import com.veedev.authservice.dto.LoginRequest;
import com.veedev.authservice.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register (RegisterRequest registerRequest);
    AuthResponse login (LoginRequest loginRequest);
}
