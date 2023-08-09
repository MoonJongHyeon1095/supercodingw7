package com.github.crudprac.config.security;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Configuration
public class SecretKeyConfig {

    @Bean
    public SecretKey secretKey(@Value("${jwt.secret-key-source}") String secretKeySource) {
        String algorithmName = SignatureAlgorithm.HS256.name();
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(secretKeySource);    // 에러 발생
        SecretKeySpec secretkeySpec = new SecretKeySpec(bytes, algorithmName);
        System.out.println(secretKeySource);
        return secretkeySpec;
    }
}
