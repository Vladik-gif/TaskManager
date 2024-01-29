package com.vladik.rest.security.service;

import com.vladik.rest.security.jwt.JwtService;
import com.vladik.rest.security.models.request.AuthenticationRequest;
import com.vladik.rest.security.models.request.RegisterRequest;
import com.vladik.rest.security.models.response.AuthenticationResponse;
import com.vladik.rest.store.entities.UserEntity;
import com.vladik.rest.store.enums.RoleEntity;
import com.vladik.rest.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var userRegister = UserEntity.builder()
                .username(request.getUsername())
                .lastName(request.getLastname())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(RoleEntity.ADMIN)
                .build();

        userRepository.save(userRegister);

        var jwtToken = jwtService.generaleToken(userRegister);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();

        var jwtToken = jwtService.generaleToken(user);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}