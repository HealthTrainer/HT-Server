package com.healthtrainer.htserver.web.dto.team;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SelectAllTeamMemberResponseDto {
    private Long teamId;
    private String teamName;
    private List<SelectTeamMemberResponseDto> userList;

}
