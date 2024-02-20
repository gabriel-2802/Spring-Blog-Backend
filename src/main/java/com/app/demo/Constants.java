package com.app.demo;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class Constants {
    public static final long JWT_EXPIRATION = 604800000L; // 7 days
    public static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public static final int ADMIN_KEY = 2802;
    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";
}
