package com.github.crudprac.service;

import com.github.crudprac.repository.UserRepository;
import com.github.crudprac.web.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    @Transactional
    public Long join(User dto) {
        dto.setPassword(encoder.encode(dto.getPassword()));

        return userRepository.save(dto.toEntity()).getId();
    }

}