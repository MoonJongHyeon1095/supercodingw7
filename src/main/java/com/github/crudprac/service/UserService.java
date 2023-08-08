package com.github.crudprac.service;

import com.github.crudprac.repository.UserJpaRepository;
import com.github.crudprac.repository.entity.UserEntity;
import com.github.crudprac.web.dto.LogoutRequest;
import com.github.crudprac.web.dto.MessageResponse;
import com.github.crudprac.web.dto.SignRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<MessageResponse> signUp(SignRequest signRequest) {
        String email = signRequest.getEmail();
        String password = signRequest.getPassword();

        if (userJpaRepository.existsByEmail(email)) {
            return ResponseEntity.status(409).body(new MessageResponse("이미 가입된 이메일입니다."));
        }

        userJpaRepository.save(UserEntity.builder()
                        .email(email)
                        .password(passwordEncoder.encode(password))
                        .build());

        return ResponseEntity.ok(new MessageResponse("회원가입이 완료되었습니다."));
    }

    public ResponseEntity<MessageResponse> login(SignRequest signRequest) {
        return null;
    }

    public ResponseEntity<MessageResponse> logout(LogoutRequest logoutRequest) {
        return null;
    }
}
