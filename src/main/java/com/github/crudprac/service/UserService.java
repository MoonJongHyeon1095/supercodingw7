package com.github.crudprac.service;

import com.github.crudprac.repository.UserJpaRepository;
import com.github.crudprac.web.dto.LogoutRequest;
import com.github.crudprac.web.dto.MessageResponse;
import com.github.crudprac.web.dto.SignRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserJpaRepository userJpaRepository;

    public ResponseEntity<MessageResponse> signUp(SignRequest signRequest) {
        String email = signRequest.getEmail();
        return null;
    }

    public ResponseEntity<MessageResponse> login(SignRequest signRequest) {
        return null;
    }

    public ResponseEntity<MessageResponse> logout(LogoutRequest logoutRequest) {
        return null;
    }
}
