package com.github.crudprac.service;

import com.github.crudprac.config.CustomUserDetails;
import com.github.crudprac.web.dto.User;
import com.github.crudprac.repository.UserRepository;
import com.github.crudprac.web.dto.UserSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

private final UserRepository userRepository;

private final HttpSession session;

/* username이 DB에 있는지 확인 */
        @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + username));

session.setAttribute("user", new UserSession(user));

/* 시큐리티 세션에 유저 정보 저장 */
return new CustomUserDetails(user);
}
}