package com.healthtrainer.htserver.web.dto.follow;

import com.healthtrainer.htserver.domain.register.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectFollowResponseDto {

    private Long userId;
    private String email;

    public SelectFollowResponseDto(User entity) {
        this.userId = entity.getId();
        this.email = entity.getEmail();
    }
}
