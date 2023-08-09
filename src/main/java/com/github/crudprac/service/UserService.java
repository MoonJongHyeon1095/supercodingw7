package com.github.crudprac.service;

import com.github.crudprac.config.security.JwtProvider;
import com.github.crudprac.repository.UserJpaRepository;
import com.github.crudprac.repository.entity.UserEntity;
import com.github.crudprac.exceptions.BadRequestException;
import com.github.crudprac.exceptions.NotFoundException;
import com.github.crudprac.dto.LogoutRequest;
import com.github.crudprac.dto.MessageResponse;
import com.github.crudprac.dto.SignRequest;
import com.github.crudprac.repository.entity.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Transactional(transactionManager = "tmJpa")
    public ResponseEntity<MessageResponse> signUp(SignRequest signRequest) {
        String email = signRequest.getEmail();
        String password = signRequest.getPassword();
        String name = signRequest.getName();

        if (email == null || password == null) throw new BadRequestException("email 혹은 password가 누락되었습니다.");

        // 이미 존재하는 email이면 409 Conflict
        if (userJpaRepository.existsByEmail(email)) return ResponseEntity.status(409).body(new MessageResponse("이미 가입된 이메일입니다."));

        String encodedPassword = encode(password);
        userJpaRepository.save(UserEntity.builder()
                        .email(email)
                        .password(encodedPassword)
                        .name(name)
                        .build());

        return ResponseEntity.ok(new MessageResponse("회원가입이 완료되었습니다."));
    }

    public ResponseEntity<MessageResponse> login(SignRequest signRequest, HttpServletResponse response) {
        log.info("login()");
        String email = signRequest.getEmail();
        String password = signRequest.getPassword();
        if (email == null || password == null) throw new BadRequestException("email 혹은 password가 누락되었습니다.");

        UserEntity userEntity = userJpaRepository.findByEmail(email).orElseThrow(()->new NotFoundException("존재하지 않는 email입니다."));

        String encodedPassword = userEntity.getPassword();
        if (!passwordEncoder.matches(password, encodedPassword)) return ResponseEntity.status(401).body(new MessageResponse("password가 옳지 않습니다."));

//        List<SimpleGrantedAuthority> authorities = getAuthorities(userEntity);
//        log.info("hello");
//        try {
//            Authentication newAuth = new UsernamePasswordAuthenticationToken(email, encodedPassword, authorities);
//            Authentication authentication = authenticationManager.authenticate(newAuth);
//            log.info("world");
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            log.info("hello world");
//        } catch (AuthenticationException e) {
//            log.warn("인증에 실패했습니다.");
//        }

        String jwtToken = jwtProvider.createToken(email, null);
        response.setHeader(jwtProvider.getHeaderName(), jwtToken);

        return ResponseEntity.ok(new MessageResponse("로그인이 성공적으로 완료되었습니다."));
    }

    private List<SimpleGrantedAuthority> getAuthorities(UserEntity userEntity) {
        return Stream.of(userEntity.getAuthority().name()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public ResponseEntity<MessageResponse> logout(LogoutRequest logoutRequest) {


        return ResponseEntity.ok(new MessageResponse("로그아웃되었습니다."));
    }

    private String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
