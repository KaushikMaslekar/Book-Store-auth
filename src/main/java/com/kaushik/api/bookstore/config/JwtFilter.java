package com.kaushik.api.bookstore.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kaushik.api.bookstore.service.JWTService;
import com.kaushik.api.bookstore.service.impl.UserInfoUserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // Bearer JWTTOKEN-Value
        String jwtToken = null;
        String username = null;

        System.out.println("Auth Header: " + authHeader);
        System.out.println("Request URI: " + request.getRequestURI());

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7);
            username = jwtService.extractUserName(jwtToken);
            System.out.println("Extracted username: " + username);
        }

        if (username != null
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserInfoUserDetailsServiceImpl userDetailsService
                    = applicationContext.getBean(UserInfoUserDetailsServiceImpl.class);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            System.out.println("UserDetails loaded: " + userDetails.getUsername());
            System.out.println("Authorities: " + userDetails.getAuthorities());

            if (jwtService.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                System.out.println("Authentication set successfully");
            } else {
                System.out.println("Token validation failed");
            }
        }
        filterChain.doFilter(request, response);
    }
}
