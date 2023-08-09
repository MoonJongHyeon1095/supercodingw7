package com.github.crudprac.service;

import com.github.crudprac.repository.UserJpaRepository;
import com.github.crudprac.repository.details.UserAuthDetails;
import com.github.crudprac.repository.entity.UserEntity;
import com.github.crudprac.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAuthDetailsService implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserAuthDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userJpaRepository.findByEmail(email).orElseThrow(()->new NotFoundException("해당 email을 찾지 못했습니다."));

        return UserAuthDetails.builder()
                .userId(userEntity.getId())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .authorities(List.of(userEntity.getAuthority().name()))
                .build();
    }
}
