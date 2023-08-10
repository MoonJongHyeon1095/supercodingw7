package com.github.crudprac.controller;

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

    /**
     * 유저 회원가입을 진행합니다.
     *
     * @param signRequest email, password, username을 담고 있는 SignRequest 객체입니다.
     *                    email, password, username은 null이 될 수 없습니다.
     * @return 200 OK: 회원가입에 성공하면 "회원가입이 완료되었습니다" 메세지가 담긴 MessageResponse 객체를 반환합니다.
     */
    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> signUp(@RequestBody SignRequest signRequest) {
        return userService.signUp(signRequest);
    }

    /**
     * 유저 로그인을 진행합니다.
     *
     * @param signRequest 로그인 하는 유저의 email, password를 담고 있는 SignRequest 객체입니다.
     *                    email, password는 null이 될 수 없습니다.
     * @return 200 OK: "로그인이 성공적으로 완료되었습니다" 메세지가 담긴 MessageResponse 객체를 반환합니다.
     */
    @PostMapping("/login")
    public ResponseEntity<MessageResponse> login(@RequestBody SignRequest signRequest, HttpServletResponse response) {
        return userService.login(signRequest, response);
    }

    /**
     * 유저의 로그아웃을 진행합니다.
     *
     * @param logoutRequest email이 담긴 logoutRequest 객체입니다.
     *                      email은 null이 될 수 없습니다.
     * @return 200 OK: "로그아웃되었습니다." 메세지가 담긴 MessageResponse 객체를 반환합니다.
     */
    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logout(@RequestBody LogoutRequest logoutRequest) {
        return userService.logout(logoutRequest);
    }
}
