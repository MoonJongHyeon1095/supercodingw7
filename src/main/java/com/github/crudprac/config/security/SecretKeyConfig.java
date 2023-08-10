package com.github.crudprac.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Configuration
public class SecretKeyConfig {

    @Bean
    public SecretKey secretKey(@Value("${jwt.secret-key-source}") String secretKeySource) throws NoSuchAlgorithmException{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] sha256SecretKeySource = digest.digest(secretKeySource.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(Base64.getEncoder().encode(sha256SecretKeySource), "HmacSHA256");
    }
}
