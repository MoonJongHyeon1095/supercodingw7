package com.github.crudprac.web.dto;

import com.github.crudprac.entity.Role;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserSession implements Serializable {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private Role role;

    /* Entity -> Dto */
    public UserSession(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}