package com.healthtrainer.htserver.web.dto.login;

import com.healthtrainer.htserver.domain.register.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCreateRequestDto {
    private String name;
    private Integer age;
    private String sex;
    private Integer height;
    private Integer weight;
    private String email;
    private boolean profileState;

    @Builder
    public UserCreateRequestDto(String name, Integer age, String sex, Integer height, Integer weight, String email, boolean profileState) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.email = email;
        this.profileState = profileState;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .age(age)
                .sex(sex)
                .height(height)
                .weight(weight)
                .email(email)
                .profileState(toProfileState(profileState))
                .build();
    }

    public String toProfileState(boolean profileState) {
        if (profileState) {
            return "Y";
        } else {
            return "N";
        }
    }
}
