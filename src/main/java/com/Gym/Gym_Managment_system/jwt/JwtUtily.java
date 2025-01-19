package com.Gym.Gym_Managment_system.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;

@Component
public class JwtUtily {

    // Extract JWT from Authorization Header
    public String getJwtFromRequest(HttpServletRequest request) {
        // Example: Extract Bearer Token
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Remove "Bearer " prefix
        }
        return null;
    }

    // Validate the token (e.g., check expiration, signature, etc.)
    public boolean validateToken(String token) {
        // Example validation logic (adjust depending on your JWT provider)
        return token != null && !isTokenExpired(token);
    }

    // Check if the token is expired
    private boolean isTokenExpired(String token) {
        // Implement token expiration logic (example assumes claim "exp")
        // Use a JWT library like io.jsonwebtoken (JJWT) to parse and validate claims
        return false; // Replace with actual logic
    }

    // Build Authentication object
    public Authentication getAuthentication(String token) {
        // Assuming the token contains the username/roles (replace with actual logic)
        String username = "exampleUser"; // Extract from token
        return new UsernamePasswordAuthenticationToken(username, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}