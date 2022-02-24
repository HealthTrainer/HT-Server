package com.healthtrainer.htserver.service.team;

import com.healthtrainer.htserver.config.CustomUserDetailService;
import com.healthtrainer.htserver.config.JwtAuthenticationProvider;
import com.healthtrainer.htserver.domain.calendar.Calendar;
import com.healthtrainer.htserver.domain.calendar.CalendarHistory;
import com.healthtrainer.htserver.domain.calendar.CalendarHistoryRepository;
import com.healthtrainer.htserver.domain.calendar.CalendarRepository;
import com.healthtrainer.htserver.domain.register.User;
import com.healthtrainer.htserver.domain.register.UserRepository;
import com.healthtrainer.htserver.domain.team.Team;
import com.healthtrainer.htserver.domain.team.TeamRepository;
import com.healthtrainer.htserver.domain.team.TeamUser;
import com.healthtrainer.htserver.domain.team.TeamUserRepository;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.calendar.CalendarHistoryAllResponseDto;
import com.healthtrainer.htserver.web.dto.calendar.CalendarHistoryResponseDto;
import com.healthtrainer.htserver.web.dto.calendar.CalendarTimeResponseDto;
import com.healthtrainer.htserver.web.dto.team.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final CustomUserDetailService userDetailService;

    private final TeamRepository teamRepository;
    private final TeamUserRepository teamUserRepository;

    private final CalendarRepository calendarRepository;
    private final CalendarHistoryRepository calendarHistoryRepository;
    private final UserRepository userRepository;

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
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 팀입니다. teamId=" + teamId));

        List<TeamUser> teamUsers = teamUserRepository.findAllByTeam(team);
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
            temp.setState(t.getTeamState());
            temp.setTeamNumber(t.getTeamNumber());
            forReturn.add(temp);
        }

        return new ResponseDto("SUCCESS",forReturn);
    }

    public ResponseDto joinTeam(ServletRequest request, Long teamId, JoinTeamRequestDto
                                joinTeamRequestDto) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User me = (User) userDetailService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        Team team = teamRepository.findById(teamId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 팀입니다. teamId=" + teamId));
        // 가입하려는 팀 찾음

        if(teamUserRepository.findByTeamAndUser(team,me) != null){
            return new ResponseDto("FAIL","이미 속한 그룹입니다.");
            // 이미 팀에 속해있을 경우
        }

        if(teamUserRepository.countByTeam(team)>=team.getTeamNumber()){ // 팀의 인원 수가 다 찾는지 확인
            return new ResponseDto("FAIL","팀의 인원이 다 찼습니다");
        }

        if(team.getTeamState().equals("Y")){ // 비밀번호가 있을 경우
            System.out.println(team.getTeamPassword());
            System.out.println(joinTeamRequestDto.getPassword());

            if(team.getTeamPassword().equals(joinTeamRequestDto.getPassword())) { // 비밀번호가 맞을 경우
                System.out.println(team.getTeamPassword());
                System.out.println(joinTeamRequestDto.getPassword());

                TeamUser teamUser = new TeamUser(team, me, "member");
                teamUserRepository.save(teamUser);
                // 팀 가입 및 DB에 저장

                return new ResponseDto("SUCCESS",teamId);
                // 가입한 팀의 id 값 반환
            }
        }

        else if(team.getTeamState().equals("N")){
            TeamUser teamUser = new TeamUser(team, me, "member");
            teamUserRepository.save(teamUser);
            // 팀 가입 및 DB에 저장

            return new ResponseDto("SUCCESS",teamId);
            // 가입한 팀의 id 값 반환
        }

        return new ResponseDto("FAIL","비밀번호가 틀렸습니다.");
        // 비밀번호가 틀렸을 경우
    }

    public ResponseDto withdrawTeam(ServletRequest request, Long teamId) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User me = (User) userDetailService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        Team team = teamRepository.findById(teamId)
                        .orElseThrow(()->new IllegalArgumentException("존재하지 않는 팀입니다. teamId=" + teamId));

        TeamUser teamUser = teamUserRepository.findByTeamAndUser(team, me);

        teamUserRepository.delete(teamUser);

        return new ResponseDto("SUCCESS", teamId);
    }

    public ResponseDto selectTimeAllMember(Long teamId) {

        List<MemberTimeResponseDto> forReturn = new ArrayList<>();

        Team team = teamRepository.findById(teamId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 팀입니다. teamId=" + teamId));

        List<TeamUser> teamUsers = teamUserRepository.findAllByTeam(team);

        for(TeamUser t : teamUsers) {

            List<Calendar> calendars = calendarRepository.findAllById(t.getUser().getId());
            MemberTimeResponseDto temp1 = new MemberTimeResponseDto();
            List<CalendarTimeResponseDto> temp2 = new ArrayList<>();

            for (Calendar c : calendars) {
                CalendarTimeResponseDto temp3 = new CalendarTimeResponseDto();
                temp3.setTime(c.getTime());
                temp3.setDate(c.getDate());

                temp2.add(temp3);
            }
            temp1.setCalendarTimeResponseDtoList(temp2);

            User user = userRepository.findById(t.getUser().getId())
                    .orElseThrow(()->new IllegalArgumentException("존재하지 않는 유저입니다. id=" + t.getUser().getId()));

            temp1.setName(user.getName());
            forReturn.add(temp1);
        }

        return new ResponseDto("SUCCESS",forReturn);
    }

    public ResponseDto selectExerciseHistoryAllMember(Long teamId) {

        List<AllTeamMemberHistoryResponseDto> forReturn = new ArrayList<>();

        Team team = teamRepository.findById(teamId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 팀입니다. teamId=" + teamId));

        List<TeamUser> teamUsers = teamUserRepository.findAllByTeam(team);

        for(TeamUser t : teamUsers){
            AllTeamMemberHistoryResponseDto temp1 = new AllTeamMemberHistoryResponseDto();
            temp1.setName(t.getUser().getName());
            List<CalendarHistoryAllResponseDto> temp2 = new ArrayList<>();

            List<Calendar> calendars = calendarRepository.findAllById(t.getUser().getId());

            for(Calendar c1 : calendars){
                CalendarHistoryAllResponseDto temp3 = new CalendarHistoryAllResponseDto();
                temp3.setDate(c1.getDate());
                temp3.setColor(c1.getColor());
                List<CalendarHistoryResponseDto> temp4 = new ArrayList<>();

                List<CalendarHistory> calendarHistories = calendarHistoryRepository.findAllByCalendar(c1);

                for(CalendarHistory c2 : calendarHistories){
                    CalendarHistoryResponseDto temp5 = new CalendarHistoryResponseDto();
                    temp5.setName(c2.getExerciseName());
                    temp5.setType(c2.getExerciseType());
                    temp5.setCounter(c2.getExerciseCount());
                    temp5.setSet(c2.getExerciseSet());
                    temp5.setTime(c2.getExerciseTime());
                    temp4.add(temp5);
                }
                temp3.setCalendarHistoryResponseDtos(temp4);
                temp2.add(temp3);
            }
            temp1.setCalendarHistoryAllResponseDtoList(temp2);
            forReturn.add(temp1);
        }

        return new ResponseDto("SUCCESS",forReturn);
    }

    public ResponseDto selectTeamMember(Long userId) {
        List<SelectAllTeamMemberResponseDto> forReturn = new ArrayList<>();

        User user = userRepository.findById(userId)
            .orElseThrow(()->new IllegalArgumentException("존재하지 않는 유저입니다. userId=" + userId));

        List<TeamUser> teamUsers = teamUserRepository.findAllByUser(user);

        for(TeamUser t1 : teamUsers){
            SelectAllTeamMemberResponseDto temp1 = new SelectAllTeamMemberResponseDto();
            temp1.setTeamId(t1.getTeam().getTeamId());
            temp1.setTeamName(t1.getTeam().getTeamName());

            List<TeamUser> temp2 = teamUserRepository.findAllByTeam(t1.getTeam());
            List<SelectTeamMemberResponseDto> temp3 = new ArrayList<>();
            for(TeamUser t2 : temp2){
                SelectTeamMemberResponseDto temp4 = new SelectTeamMemberResponseDto();
                temp4.setId(t2.getUser().getId());
                temp4.setName(t2.getUser().getName());

                temp3.add(temp4);
            }
            temp1.setUserList(temp3);
            forReturn.add(temp1);
        }
        return new ResponseDto("SUCCESS",forReturn);
    }
}