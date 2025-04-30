package com.veedev.authservice.impl;

import com.veedev.authservice.dto.AuthResponse;
import com.veedev.authservice.dto.LoginRequest;
import com.veedev.authservice.dto.RegisterRequest;
import com.veedev.authservice.model.User;
import com.veedev.authservice.repository.UserRepository;
import com.veedev.authservice.service.AuthService;
import com.veedev.authservice.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            if (userRepository.findUserByEmail(registerRequest.getEmail()).isPresent()) {
                throw new RuntimeException("User already exists");
            }
        }
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);
        return new AuthResponse(jwtUtil.generateToken(user));
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByUsername(loginRequest.getUsernameOrEmail());
        if (user.isEmpty()) {
            user = userRepository.findUserByEmail(loginRequest.getUsernameOrEmail());
            if (user.isEmpty()){
                throw new RuntimeException("User not found");
            }
        }
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return new AuthResponse(jwtUtil.generateToken(user.get()));
    }
}
