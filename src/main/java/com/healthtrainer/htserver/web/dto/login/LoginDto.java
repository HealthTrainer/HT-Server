package com.healthtrainer.htserver.web.dto.login;

import com.healthtrainer.htserver.domain.register.User;
import lombok.Getter;

@Getter
public class LoginDto {

    private String email;
    private String password;

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }
}