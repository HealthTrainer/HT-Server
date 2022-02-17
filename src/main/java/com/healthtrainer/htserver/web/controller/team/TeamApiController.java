package com.healthtrainer.htserver.web.controller.team;

import com.healthtrainer.htserver.service.team.TeamService;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.team.CreateTeamRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;

@Controller
@RequiredArgsConstructor
public class TeamApiController {

    private final TeamService teamService;

    @PostMapping("/users/team")
    @ResponseBody
    public ResponseDto addTeam(ServletRequest request, @RequestBody CreateTeamRequestDto requestTeamDto){
        return teamService.addTeam(request, requestTeamDto);
    }

    @DeleteMapping("/users/team/{teamId}")
    @ResponseBody
    public ResponseDto deleteTeamByTeamName(ServletRequest request, @PathVariable Long teamId){
        return teamService.deleteTeam(request, teamId);
    }
}
