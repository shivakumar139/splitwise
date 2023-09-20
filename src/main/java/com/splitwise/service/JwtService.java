package com.splitwise.service;


import org.springframework.security.core.userdetails.UserDetails;
import java.util.Map;

public interface JwtService {

    String createToken(UserDetails user, Map<String, Object> extraClaims);

    String extractUserName(String token);

    boolean isValidToken(String token);
}

