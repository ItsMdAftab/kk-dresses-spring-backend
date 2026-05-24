package com.aftab.kkdresses.security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final String SECRET =
            "MY_SUPER_SECRET_KEY_FOR_KK_DRESSES_2026_PROJECT";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String path =
                request.getServletPath();

        // ALLOW LOGIN & REGISTER APIs

        if (path.equals("/login")
                || path.equals("/register-owner")
                || path.equals("/register-worker")) {

            filterChain.doFilter(
                    request,
                    response
            );

            return;
        }

        // GET AUTH HEADER

        String authHeader =
                request.getHeader(
                        "Authorization"
                );

        if (authHeader == null
                || !authHeader.startsWith(
                        "Bearer "
                )) {

            response.setStatus(401);

            response.getWriter().write(
                    "Missing Token ❌"
            );

            return;
        }

        try {

            String token =
                    authHeader.substring(7);

            Claims claims =
                    Jwts.parserBuilder()
                            .setSigningKey(
                                    JwtUtil.KEY
                            )
                            .build()
                            .parseClaimsJws(token)
                            .getBody();

            String username =
                    claims.getSubject();

            System.out.println(
                    "Authenticated User: "
                    + username
            );

        } catch (Exception e) {

            response.setStatus(401);

            response.getWriter().write(
                    "Invalid Token ❌"
            );

            return;
        }

        filterChain.doFilter(
                request,
                response
        );
    }
}