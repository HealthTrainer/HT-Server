package com.healthtrainer.htserver.web.dto.login;

import com.healthtrainer.htserver.domain.register.User;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class UserFindResponseDto {

    private String name;
    private Integer age;
    private String sex;
    private Integer height;
    private Integer weight;
    private String email;
    private String profileState;

    public UserFindResponseDto(User entity) {
        this.name = entity.getName();
        this.age = entity.getAge();
        this.sex = entity.getSex();
        this.height = entity.getHeight();
        this.weight = entity.getWeight();
        this.email = entity.getEmail();
        this.profileState = entity.getProfileState();
    }
}
