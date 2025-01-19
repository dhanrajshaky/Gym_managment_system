package com.Gym.Gym_Managment_system.jwt;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtily jwtUtily;

    // Constructor for dependency injection
    public JwtAuthenticationFilter(JwtUtily jwtUtily) {
        this.jwtUtily = jwtUtily;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Extract JWT from the request
        String jwtToken = jwtUtily.getJwtFromRequest(request);

        // Validate token and update security context
        if (jwtToken != null && jwtUtily.validateToken(jwtToken)) {
            SecurityContextHolder.getContext().setAuthentication(jwtUtily.getAuthentication(jwtToken));
        }

        // Proceed with the filter chain
        filterChain.doFilter(request, response);
    }
}