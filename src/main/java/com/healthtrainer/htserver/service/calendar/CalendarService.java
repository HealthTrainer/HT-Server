package com.healthtrainer.htserver.service.calendar;

import com.healthtrainer.htserver.config.JwtAuthenticationProvider;
import com.healthtrainer.htserver.domain.calendar.Calendar;
import com.healthtrainer.htserver.domain.calendar.CalendarHistory;
import com.healthtrainer.htserver.domain.calendar.CalendarHistoryRepository;
import com.healthtrainer.htserver.domain.calendar.CalendarRepository;
import com.healthtrainer.htserver.domain.exercise.ExerciseHistory;
import com.healthtrainer.htserver.domain.exercise.ExerciseHistoryRepository;
import com.healthtrainer.htserver.domain.exercise.ExerciseList;
import com.healthtrainer.htserver.domain.exercise.ExerciseListRepository;
import com.healthtrainer.htserver.domain.register.User;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.calendar.CalendarHistoryAllResponseDto;
import com.healthtrainer.htserver.web.dto.calendar.CalendarHistoryRequestDto;
import com.healthtrainer.htserver.web.dto.calendar.CalendarHistoryResponseDto;
import com.healthtrainer.htserver.web.dto.calendar.CalendarRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserDetailsService userDetailsService;

    private final CalendarRepository calendarRepository;
    private final CalendarHistoryRepository calendarHistoryRepository;

    private final ExerciseHistoryRepository exerciseHistoryRepository;
    private final ExerciseListRepository exerciseListRepository;

    private int color_green = 0;
    private int color_red = 0;


    public ResponseDto addCalendarHistory(ServletRequest request, String title,
                                   Long exerciseId, CalendarHistoryRequestDto calendarRequestDto) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User me = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        Calendar calendar = calendarRepository.findByDate(calendarRequestDto.getDate());
        // 날짜를 이용해 캘린더 선택

        CalendarHistory calendarHistory = new CalendarHistory();
        calendarHistory.setCalendar(calendar);
        calendarHistory.setExerciseCount(calendarRequestDto.getCount());
        calendarHistory.setExerciseName(calendarRequestDto.getName());
        calendarHistory.setExerciseSet(calendarRequestDto.getSet());
        calendarHistory.setExerciseType(calendarRequestDto.getType());
        calendarHistory.setExerciseWeight(calendarRequestDto.getWeight());
        calendarHistory.setExerciseTime(calendarRequestDto.getTime());

        ExerciseHistory exerciseHistory = exerciseHistoryRepository.findById(exerciseId)
                .orElseThrow(()->new IllegalArgumentException("루틴에 설정되어 있는 운동이 아닙니다."));

        if(calendarHistory.getExerciseCount() >= exerciseHistory.getExerciseCount() &&
        calendarHistory.getExerciseSet() >= exerciseHistory.getExerciseSet()&&
        calendarHistory.getExerciseWeight() >= exerciseHistory.getExerciseWeight()){
            calendarHistory.setColor("green");
        }
        else{
            calendarHistory.setColor("red");
        }
        calendarHistoryRepository.save(calendarHistory);

        return new ResponseDto("SUCCESS",calendarHistory.getColor());
    }

    public ResponseDto addCalendar(ServletRequest request, String title, CalendarRequestDto calendarRequestDto) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User me = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        ExerciseList exerciseList = exerciseListRepository.findByUserAndTitle(me,title);

        Calendar calendar = new Calendar();
        calendar.setDate(calendarRequestDto.getDate());
        calendar.setTime(calendarRequestDto.getTime());
        calendar.setId(me.getId());
        calendar.setExerciseList(exerciseList);

        calendarRepository.save(calendar);
        return new ResponseDto("SUCCESS",calendar.getCalendarId()); // calendarId 값 반환
    }

    public ResponseDto findAllCalendarHistory(ServletRequest request, String title) {

        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User me = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        List<Calendar> calendars = calendarRepository.findAllById(me.getId());

        List<CalendarHistoryAllResponseDto> calendarHistoryAllResponseDtos = new ArrayList<>();

        for(Calendar c1 : calendars) {

            CalendarHistoryAllResponseDto temp1 = new CalendarHistoryAllResponseDto();
            temp1.setDate(c1.getDate()); // 날짜 설정

            List<CalendarHistoryResponseDto> temp2 = new ArrayList<>();

            List<CalendarHistory> calendarHistories = calendarHistoryRepository.findAllByCalendar(c1);
            for (CalendarHistory c2 : calendarHistories) {
                CalendarHistoryResponseDto temp3 = new CalendarHistoryResponseDto();
                temp3.setName(c2.getExerciseName());
                temp3.setType(c2.getExerciseType());
                temp3.setCounter(c2.getExerciseCount());
                temp3.setSet(c2.getExerciseSet());
                temp3.setTime(c2.getExerciseTime());
                temp2.add(temp3);


                if(c2.getColor().equals("green")){ color_green = color_green + 1; }
                else if(c2.getColor().equals("red")){ color_red = color_red + 1; }
            }

            if(color_green >= color_red){ temp1.setColor("green"); }
            else if(color_green < color_red){ temp1.setColor("red"); }

            temp1.setCalendarHistoryResponseDtos(temp2);
            color_green = 0;
            color_red = 0;

            calendarHistoryAllResponseDtos.add(temp1);
        }

        return new ResponseDto("SUCCESS",calendarHistoryAllResponseDtos);
    }
}