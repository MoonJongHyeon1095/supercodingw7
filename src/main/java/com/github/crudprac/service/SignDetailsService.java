package com.github.crudprac.service;

import com.github.crudprac.exceptions.NotFoundException;
import com.github.crudprac.repository.SignJpaRepository;
import com.github.crudprac.repository.details.SignDetails;
import com.github.crudprac.repository.entity.SignEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignDetailsService implements UserDetailsService {

    private final SignJpaRepository signJpaRepository;

    /**
     * 메서드의 이름은 Username이 들어있지만 실제 email을 이용해 SignDetails 객체를 가져옵니다.
     *
     * @param email 유저의 email
     * @return SignDetails 객체
     */
    @Override
    public SignDetails loadUserByUsername(String email) {
        SignEntity sign = signJpaRepository.findByEmail(email).orElseThrow(()->new NotFoundException("존재하지 않는 email입니다."));

        return SignDetails.builder()
                .signId(sign.getId())
                .email(sign.getEmail())
                .password(sign.getPassword())
                .user(sign.getUser())
                .build();
    }
}
