package com.aftab.kkdresses.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

    // SECRET KEY

    private static final String SECRET =
            "MY_SUPER_SECRET_KEY_FOR_KK_DRESSES_2026_PROJECT";

    public static final Key KEY =
            new SecretKeySpec(
                    SECRET.getBytes(),
                    SignatureAlgorithm.HS256
                            .getJcaName()
            );

    // GENERATE TOKEN

    public static String generateToken(
            String username
    ) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                + 1000 * 60 * 60 * 24
                        )
                )
                .signWith(KEY)
                .compact();
    }
}