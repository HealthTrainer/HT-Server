package com.healthtrainer.htserver.web.dto.register;

import com.healthtrainer.htserver.domain.register.Role;
import com.healthtrainer.htserver.domain.register.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RegisterDto {

    private String email;
    private String password;

    public User toEntity(){
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }
}
