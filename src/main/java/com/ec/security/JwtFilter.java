package com.ec.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String header =
                request.getHeader("Authorization");

        if (header != null &&
                header.startsWith("Bearer ")) {

            String token =
                    header.substring(7);

            if (JwtUtil.validateToken(token)) {

                String email =
                        JwtUtil.extractEmail(token);

                String role =
                        JwtUtil.extractRole(token);

                System.out.println(
                        "EMAIL : " + email);

                System.out.println(
                        "ROLE : " + role);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                Collections.singletonList(
                                        new SimpleGrantedAuthority(
                                                "ROLE_" + role)));

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(auth);

                System.out.println(
                        "Authorities : "
                        + auth.getAuthorities());
            }
        }

        filterChain.doFilter(
                request,
                response);
    }
}