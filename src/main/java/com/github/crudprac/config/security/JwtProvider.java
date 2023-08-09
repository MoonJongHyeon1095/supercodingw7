package com.github.crudprac.config.security;

import com.github.crudprac.repository.details.UserAuthDetails;
import com.github.crudprac.service.UserAuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
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
@Getter
@RequiredArgsConstructor
public class JwtProvider {
//    @Value("${jwt.secret-key-source}")
//    private String secretKeySource;
    private final SecretKey secretKey;
    private final long tokenValidMilliseconds = 1000L * 60 * 60;
    private final String headerName = "Access-Token";
    private final UserAuthDetailsService userAuthDetailsService;
//    @PostConstruct
//    void setUP() {
//        secretKey = new SecretKeySpec(Base64.getDecoder().decode(secretKeySource), SignatureAlgorithm.HS256.name());
//    }

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
        return request.getHeader(headerName);
    }

    public boolean validateToken(String jwt) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJwt(jwt).getBody();
            assert claims != null;
            return new Date().before(claims.getExpiration());
        } catch (Exception e) {
            return false;
        }
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String jwt) {
        String email = getEmail(jwt);
        if (email == null) return null;

        UserAuthDetails userAuthDetails = userAuthDetailsService.loadUserByUsername(email);
        // List<String> roles = userAuthDetails.getRoles();
        String encodedPassword = userAuthDetails.getPassword();

        return new UsernamePasswordAuthenticationToken(email, encodedPassword, null);
    }

    private String getEmail(String jwt) {
        try {
            return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJwt(jwt).getBody().getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
