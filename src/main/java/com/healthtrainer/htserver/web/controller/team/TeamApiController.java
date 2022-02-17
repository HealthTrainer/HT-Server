package com.healthtrainer.htserver.web.controller.team;

import com.healthtrainer.htserver.service.team.TeamService;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.team.CreateTeamRequestDto;
import com.healthtrainer.htserver.web.dto.team.JoinTeamRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;

@Controller
@RequiredArgsConstructor
public class TeamApiController {

    private final TeamService teamService;

    @PostMapping("/users/team")
    @ResponseBody // 팀 생성
    public ResponseDto addTeam(ServletRequest request, @RequestBody CreateTeamRequestDto requestTeamDto){
        return teamService.addTeam(request, requestTeamDto);
    }

    @DeleteMapping("/users/team/{teamId}")
    @ResponseBody // 팀 삭제
    public ResponseDto deleteTeamByTeamName(ServletRequest request, @PathVariable Long teamId){
        return teamService.deleteTeam(request, teamId);
    }

    @GetMapping("/users/team/{keyword}")
    @ResponseBody // "keyword"를 통해서 팀 전체 조회
    public ResponseDto selectAllTeamByKeyword(ServletRequest request, @PathVariable String keyword){
        return teamService.selectAllTeamByKeyword(request, keyword);
    }

    @PostMapping("/users/team/{teamId}/join")
    @ResponseBody // 팀 가입
    public ResponseDto joinTeam(ServletRequest request, @PathVariable Long teamId,
                                @RequestBody JoinTeamRequestDto joinTeamRequestDto){
        return teamService.joinTeam(request, teamId, joinTeamRequestDto);
    }
}
