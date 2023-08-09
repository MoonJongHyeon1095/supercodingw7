package com.github.crudprac.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class LoginRequestDto {
    private String email;
    private String password;
}
