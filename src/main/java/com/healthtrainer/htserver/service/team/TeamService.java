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
import com.healthtrainer.htserver.web.dto.team.JoinTeamRequestDto;
import com.healthtrainer.htserver.web.dto.team.TeamResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

        List<TeamUser> teamUsers = teamUserRepository.findByTeam(team);
        for(TeamUser t : teamUsers){
            teamUserRepository.delete(t);
            // team_user 테이블에 속해있는 팀원들 삭제(외래키 참조 무결성때문에 먼저), 이 부분 기술블로그 작성
        }

        teamRepository.delete(team); // 팀 삭제(이후 팀 삭제)

        return new ResponseDto("SUCCESS",teamId);
    }

    public ResponseDto selectAllTeamByKeyword(ServletRequest request, String keyword) {
        List<Team> teams = teamRepository.findAllByKeyword(keyword);
        List<TeamResponseDto> forReturn = new ArrayList<>();

        for(Team t : teams){
            TeamResponseDto temp = new TeamResponseDto();
            temp.setTeamName(t.getTeamName());
            temp.setTeamId(t.getTeamId());
            forReturn.add(temp);
        }

        return new ResponseDto("SUCCESS",forReturn);
    }

    public ResponseDto joinTeam(ServletRequest request, Long teamId, JoinTeamRequestDto
                                joinTeamRequestDto) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User me = (User) userDetailService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        Team team = teamRepository.findById(teamId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 팀입니다. id=" + teamId));
        // 가입하려는 팀 찾음

        if(teamUserRepository.countByTeam(team)>=team.getTeamNumber()){ // 팀의 인원 수가 다 찾는지 확인
            return new ResponseDto("FAIL","팀의 인원이 다 찼습니다");
        }

        if(team.getTeamState().equals("Y")){ // 비밀번호가 있을 경우
            System.out.println(team.getTeamPassword());
            System.out.println(joinTeamRequestDto.getPassword());

            if(team.getTeamPassword().equals(joinTeamRequestDto.getPassword())) { // 비밀번호가 참
                System.out.println(team.getTeamPassword());
                System.out.println(joinTeamRequestDto.getPassword());

                TeamUser teamUser = new TeamUser(team, me, "member");
                teamUserRepository.save(teamUser);
                // 팀 가입 및 DB에 저장

                return new ResponseDto("SUCCESS",teamId);
                // 가입한 팀의 id 값 반환
            }
        }

        return new ResponseDto("FAIL","비밀번호가 틀렸습니다.");
        // 비밀번호가 틀렸을 경우
    }
}