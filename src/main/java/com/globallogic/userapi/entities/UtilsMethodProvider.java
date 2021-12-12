package com.globallogic.userapi.entities;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class UtilsMethodProvider {

    private UtilsMethodProvider() { throw new IllegalStateException("Utility class"); }

    /**
     * Generate JWT token
     * @param subject user email
     * @return JWT Token.
     */
    public static String tokenGeneration(String subject) {
        return Jwts
                .builder()
                .setSubject(subject)
                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                .compact();
    }
}
