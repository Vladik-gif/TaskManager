package com.vladik.rest.security.service;

import com.vladik.rest.security.models.request.AuthenticationRequest;
import com.vladik.rest.security.models.request.RegisterRequest;
import com.vladik.rest.security.models.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
