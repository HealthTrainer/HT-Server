package com.healthtrainer.htserver.web.dto.login;

import com.healthtrainer.htserver.domain.register.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {

    private Long userId;

    public UserResponseDto(User entity) {
        this.userId = entity.getId();
    }
}
