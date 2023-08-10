package com.github.crudprac.config.security;

import com.github.crudprac.exceptions.JwtIsNotValidException;
import com.github.crudprac.repository.details.SignDetails;
import com.github.crudprac.service.SignDetailsService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
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
//    private final SignDetailsService signDetailsService;

    /**
     * JWT 토큰을 생성합니다.
     *
     * @param email 유저의 email
     * @param authorities 유저의 권한들을 담은 리스트
     * @return JWT 토큰
     */
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

    /**
     * 리퀘스트 헤더에 포함된 JWT 토큰을 가져옵니다.
     * @param request HttpServletRequest 객체
     * @return JWT 토큰
     */
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(headerName);
    }

    /**
     * JWT 토큰이 유효한지 검사합니다.
     *
     * @param jwt 검사할 JWT 토큰
     * @return 유효한 토큰일 경우 true
     */
    public boolean validateToken(String jwt) {
        try {
            parseClaims(jwt);
            return true;
        } catch (JwtIsNotValidException e) {
            return false;
        }
    }

    /**
     * email, 인코딩된 pw, 권한 정보들을 담고 있는 Authentication 객체를 생성합니다.
     *
     * @param jwt JWT 토큰
     * @return authentication
     */
    public UsernamePasswordAuthenticationToken createAuthentication(String jwt) {
        String email = getEmail(jwt).orElseThrow(()->new JwtIsNotValidException("신뢰할 수 없는 토큰입니다."));
//        SignDetails signDetails = (SignDetails) signDetailsService.loadUserByUsername(email);
//        String encodedPassword = signDetails.getPassword();
        List<String> authorities = getAuthorities(jwt).orElseThrow(()->new JwtIsNotValidException("신뢰할 수 없는 토큰입니다."));
        List<SimpleGrantedAuthority> GrantedAuthorities = authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(email, jwt, GrantedAuthorities);
    }

    /**
     * JWT 토큰을 파싱하여 Claims를 반환합니다.
     *
     * @param jwt 파싱할 JWT 토큰
     * @return Claims
     * @throws JwtIsNotValidException JWT 토큰이 유효하지 않을 경우 발생합니다.
     */
    public Claims parseClaims(String jwt) throws JwtIsNotValidException {
        try {
            return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();
        } catch (ExpiredJwtException e) {
            throw new JwtIsNotValidException("만료된 토큰입니다.");
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            throw new JwtIsNotValidException("토큰의 형식을 만족하지 않습니다.");
        } catch (SignatureException e) {
            throw new JwtIsNotValidException("신뢰할 수 없는 토큰입니다.");
        }
    }

    /**
     * JWT 토큰에서 유저의 권한 정보들을 꺼내옵니다.
     *
     * @param jwt JWT 토큰
     * @return 유저의 권한 정보를 담은 Optional 리스트
     */
    private Optional<List<String>> getAuthorities(String jwt) {
        List<?> authorities = parseClaims(jwt).get("authorities", List.class);
        if (authorities == null) return Optional.empty();
        return Optional.of(authorities.stream().map(String::valueOf).collect(Collectors.toList()));
    }

    /**
     * JWT 토큰에서 email을 꺼내옵니다.
     *
     * @param jwt JWT 토큰
     * @return Optional email
     */
    private Optional<String> getEmail(String jwt) {
        return Optional.ofNullable(parseClaims(jwt).getSubject());
    }
}
