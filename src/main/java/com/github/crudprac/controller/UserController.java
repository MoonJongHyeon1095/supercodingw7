package com.github.crudprac.controller;

import com.github.crudprac.service.UserAuthDetailsService;
import com.github.crudprac.service.UserService;
import com.github.crudprac.dto.LogoutRequest;
import com.github.crudprac.dto.MessageResponse;
import com.github.crudprac.dto.SignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserAuthDetailsService userAuthDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> signUp(@RequestBody SignRequest signRequest) {
        return userService.signUp(signRequest);
        // return userAuthDetailsService.signUp(signRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<MessageResponse> login(@RequestBody SignRequest signRequest, HttpServletResponse response) {
        return userService.login(signRequest, response);
    }

    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logout(@RequestBody LogoutRequest logoutRequest) {
        return userService.logout(logoutRequest);
    }
}
