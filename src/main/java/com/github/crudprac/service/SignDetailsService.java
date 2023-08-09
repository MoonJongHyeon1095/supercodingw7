package com.github.crudprac.service;

import com.github.crudprac.exceptions.NotFoundException;
import com.github.crudprac.repository.SignJpaRepository;
import com.github.crudprac.repository.details.SignDetails;
import com.github.crudprac.repository.entity.SignEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignDetailsService implements UserDetailsService {

    private final SignJpaRepository signJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SignEntity sign = signJpaRepository.findByEmail(email).orElseThrow(()->new NotFoundException("존재하지 않는 email입니다."));

        return SignDetails.builder()
                .signId(sign.getId())
                .email(sign.getEmail())
                .password(sign.getPassword())
                .user(sign.getUser())
                .build();
    }
}
