package com.github.crudprac.config.security;

import com.github.crudprac.repository.details.SignDetails;
import com.github.crudprac.service.SignDetailsService;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Getter
@RequiredArgsConstructor
@Slf4j
public class JwtProvider {
    private final SecretKey secretKey;
    private final long tokenValidMilliseconds = 1000L * 60 * 60;
    private final String headerName = "Access-Token";
    private final SignDetailsService signDetailsService;

    public String createToken(String email, List<String> authorities) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(email)
                .claim("authorities", authorities)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMilliseconds))
                .signWith(secretKey)
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(headerName);
    }

    public boolean validateToken(String jwt) {
        try {
            Date exp = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody().getExpiration();
            return new Date().before(exp);
        } catch (Exception e) {
            return false;
        }
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String jwt) {
        String email = getEmail(jwt);
        if (email == null) return null;

        SignDetails signDetails = (SignDetails) signDetailsService.loadUserByUsername(email);
        String encodedPassword = signDetails.getPassword();
        List<String> authorities = getAuthorities(jwt).orElseThrow(NullPointerException::new);
        List<SimpleGrantedAuthority> GrantedAuthorities = authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(email, encodedPassword, GrantedAuthorities);
    }

    private Optional<List<String>> getAuthorities(String jwt) {
        List<?> authorities = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody().get("authorities", List.class);
        if (authorities == null) return Optional.empty();
        return Optional.of(authorities.stream().map(String::valueOf).collect(Collectors.toList()));
    }

    private String getEmail(String jwt) {
        try {
            return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody().getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}