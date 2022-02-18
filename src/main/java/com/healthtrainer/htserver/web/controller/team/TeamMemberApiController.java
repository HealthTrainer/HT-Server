package com.healthtrainer.htserver.web.controller.team;

import com.healthtrainer.htserver.service.team.TeamService;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;

@Controller
@RequiredArgsConstructor
public class TeamMemberApiController {

    private final TeamService teamService;

    @ResponseBody
    @DeleteMapping("/users/team/{teamId}/member") // 팀에서 탈퇴
    public ResponseDto withdrawTeam(ServletRequest request, @PathVariable Long teamId){
        return teamService.withdrawTeam(request, teamId);
    }

    @ResponseBody
    @GetMapping("/users/team/{teamId}/member/time") // 팀원들의 운동시간 조회
    public ResponseDto selectTimeAllMember(@PathVariable Long teamId){
        return teamService.selectTimeAllMember(teamId);
    }


}
