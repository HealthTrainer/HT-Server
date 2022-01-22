package com.healthtrainer.htserver.web.dto.register;

import com.healthtrainer.htserver.domain.register.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RegisterDto {

    private String password;
    private String name;
    private String picture;
    private char sex;
    private int age;
    private int height;
    private int weight;
    private String email;
    private char profile_state;
    private char withdrawl_state;
    private String roles = "ROLE_USER";

     public User toEntity(){
        return User.builder()
                .password(password)
                .name(name)
                .picture(picture)
                .sex(sex)
                .age(age)
                .height(height)
                .weight(weight)
                .email(email)
                .profileState(profile_state)
                .withdrawlState(withdrawl_state)
                .roles(Collections.singletonList(roles))
                .build();
    }
}
