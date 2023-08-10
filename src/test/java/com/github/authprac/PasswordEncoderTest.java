package com.github.authprac;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordEncoderTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("수동 등록한 passwordEncoder를 주입 받아와 문자열 암호화")
    void test1() {
        String password = "AnakinSkywalker";

        // 암호화
        String encodePassword = passwordEncoder.encode(password);
        System.out.println("encodePassword = " + encodePassword);

        String inputPassword = "LukeSkywalker";

        // 암호문끼리 비교!
        boolean matches = passwordEncoder.matches(inputPassword, encodePassword);
        System.out.println("matches = " + matches); // 암호화할 때 사용된 값과 다른 문자열과 비교했기 때문에 false
    }
}
