package com.healthtrainer.htserver.web.dto.register;

import com.healthtrainer.htserver.domain.register.User;
import lombok.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterDto {

    private String password;
    private String name;
    private String sex;
    private Integer age;
    private Integer height;
    private Integer weight;
    private String email;
    private String profile_state;
    private String withdrawl_state;
    private String roles = "ROLE_USER";

     public User toEntity(){
        return User.builder()
                .password(password)
                .name(name)
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
