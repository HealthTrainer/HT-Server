package com.healthtrainer.htserver.web.dto.follow;

import com.healthtrainer.htserver.domain.register.User;
import lombok.Getter;

@Getter
public class FollowDto {

    private String email;
    private String name;

    public User toEntity(){
        return User.builder()
                .email(email)
                .name(name)
                .build();
    }
}
