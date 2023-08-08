package com.github.crudprac.web.controller;

import com.github.crudprac.web.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 화면 연결 Controller
 */

@Controller
public class UserIndexController {

    @GetMapping("/auth/join")
    public String join() {
        return "/user/user-join";
    }

    @PostMapping("/auth/joinProc")
    public String joinProc(User userDto) {
        userService.userJoin(userDto);

        return "redirect:/auth/login";
    }

    @GetMapping("/auth/login")
    public String login() {
        return "/user/user-login";
    }
}