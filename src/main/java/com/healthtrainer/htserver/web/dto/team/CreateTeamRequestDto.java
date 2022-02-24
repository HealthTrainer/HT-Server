package com.healthtrainer.htserver.web.dto.team;

import lombok.Getter;

@Getter
public class CreateTeamRequestDto {
    private String name;
    private Integer number;
    private String state;
    private String password;
}
