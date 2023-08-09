package com.github.crudprac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)// Spring Security 인증 기능 제외
//@EntityScan("com.github.crudprac.entity")
public class CrudpracApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudpracApplication.class, args);
    }

}

