package com.github.crudprac.config.security;

import com.github.crudprac.filters.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // 'X-Frame-Options' 헤더 설정을 'same-origin'으로 설정하여 다른 도메인에서 해당 페이지를 iframe으로 렌더링하는 것 방지
                .headers()
                    .frameOptions()
                    .sameOrigin()
                .and()
                // form 로그인 사용 비활성화
                .formLogin().disable()
                // CSRF(Cross-Site Request Forgery) 보호 비활성화
                .csrf().disable()
                // 기본적인 Http Basic 인증 비활성화
                .httpBasic().disable()
                // Remember-Me 기능 비활성화
                .rememberMe().disable()
                // 세션 관리 정책 설정: 세션을 생성하지 않음
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 접근 권한 설정
                .authorizeRequests()
                    // 지정된 경로에 대해 모두 접근 허용
                    .antMatchers("/resources/static/**", "/api/signup", "/api/login", "/api/logout").permitAll()
                // 나머지는 인증 필요
                .anyRequest().authenticated()
                .and()
                // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 추가
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
