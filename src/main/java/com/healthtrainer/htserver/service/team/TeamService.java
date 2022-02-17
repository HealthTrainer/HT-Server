package com.healthtrainer.htserver.service.team;

import com.healthtrainer.htserver.config.CustomUserDetailService;
import com.healthtrainer.htserver.config.JwtAuthenticationProvider;
import com.healthtrainer.htserver.domain.register.User;
import com.healthtrainer.htserver.domain.team.Team;
import com.healthtrainer.htserver.domain.team.TeamRepository;
import com.healthtrainer.htserver.domain.team.TeamUser;
import com.healthtrainer.htserver.domain.team.TeamUserRepository;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.team.CreateTeamRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final CustomUserDetailService userDetailService;

    private final TeamRepository teamRepository;
    private final TeamUserRepository teamUserRepository;

    public ResponseDto addTeam(ServletRequest request, CreateTeamRequestDto requestTeamDto) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User me = (User) userDetailService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        Team team = new Team();
        team.setTeamName(requestTeamDto.getName());
        team.setTeamNumber(requestTeamDto.getNumber());
        team.setTeamState(requestTeamDto.getState()); // state(비밀공개 여부)는 Y or N
        team.setTeamPassword(requestTeamDto.getPassword());

        TeamUser teamUser = new TeamUser(team, me, "leader");
        /* 처음 팀을 생성하는 유저는 항상 "leader"로 설정 */

        teamRepository.save(team);
        teamUserRepository.save(teamUser);

        return new ResponseDto("SUCCESS", team.getTeamId());
    }

    public ResponseDto deleteTeam(ServletRequest request, Long teamId) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User me = (User) userDetailService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        Team team = teamRepository.findById(teamId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 팀입니다. id=" + teamId));

        teamRepository.delete(team); // 팀 삭제

        List<TeamUser> teamUsers = teamUserRepository.findByTeam(teamId);
        for(TeamUser t : teamUsers){
            teamUserRepository.delete(t);
            // team_user 테이블에 속해있는 팀원들 삭제
        }

        return new ResponseDto("SUCCESS",teamId);
    }
}