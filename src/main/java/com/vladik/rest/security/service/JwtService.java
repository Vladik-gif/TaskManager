package com.vladik.rest.security.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {

    String extractUsername(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsTFunction);
    String generateJwtToken(UserDetails userDetails);

    String generateJwtToken(Map<String, Object> extraClaims, UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

}
