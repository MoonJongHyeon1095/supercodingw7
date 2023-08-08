package com.github.crudprac.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    @Value("${jwt.secret-key-source}")
    private String secretKeySource;
    private SecretKey secretKey;
    private final long tokenValidMilliseconds = 1000L * 60 * 60;

    @PostConstruct
    void setUP() {
        secretKey = new SecretKeySpec(Base64.getDecoder().decode(secretKeySource), SignatureAlgorithm.HS256.name());
    }

    public String createToken(String email, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("roles", roles);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + tokenValidMilliseconds))
                .signWith(secretKey)
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Access-Token");
    }

    public boolean validateToken(String jwt) {
        try {
            Claims claims = parseClaimsJwt(jwt);
            assert claims != null;
            return new Date().before(claims.getExpiration());
        } catch (Exception e) {
            return false;
        }
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String jwt) {
        String email = getEmail(jwt);
        List<String> roles = getRoles(jwt);
        if (email == null) return null;
        return new UsernamePasswordAuthenticationToken(email, null, null);
    }

    private List<String> getRoles(String jwt) {
        Claims claims = parseClaimsJwt(jwt);
        if (claims == null) return null;

        Object rolesObj =  claims.get("roles");
        if (!(rolesObj instanceof List)) return null;

        List<?> roles = (List<?>) rolesObj;
        return roles.stream().map(String::valueOf).collect(Collectors.toList());
    }

    private Claims parseClaimsJwt(String jwt) {
        try {
            return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJwt(jwt).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    private String getEmail(String jwt) {
        try {
            return parseClaimsJwt(jwt).getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
