package com.healthtrainer.htserver.service.exercise;

import com.healthtrainer.htserver.config.JwtAuthenticationProvider;
import com.healthtrainer.htserver.domain.exercise.ExerciseHistory;
import com.healthtrainer.htserver.domain.exercise.ExerciseHistoryRepository;
import com.healthtrainer.htserver.domain.exercise.ExerciseList;
import com.healthtrainer.htserver.domain.exercise.ExerciseListRepository;
import com.healthtrainer.htserver.domain.register.User;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.exercise.ExerciseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseHistoryService {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserDetailsService userDetailsService;
    private final ExerciseHistoryRepository exerciseHistoryRepository;
    private final ExerciseListRepository exerciseListRepository;
    private ExerciseList exerciseList;

    @Transactional
    public ResponseDto addExerciseHistoryByTitle(ServletRequest request, String title, ExerciseDto exerciseDto) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User me = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        ExerciseHistory exerciseHistory = new ExerciseHistory();

        ExerciseList exerciseList = exerciseListRepository.findByUserAndTitle(me,title);
        exerciseHistory.setExerciseList(exerciseList);

        exerciseHistory.setExerciseName(exerciseDto.getName());
        exerciseHistory.setExerciseType(exerciseDto.getType());
        exerciseHistory.setExerciseSet(exerciseDto.getSet());
        exerciseHistory.setExerciseCount(exerciseDto.getCount());
        exerciseHistory.setExerciseWeight(exerciseDto.getWeight());

        exerciseHistoryRepository.save(exerciseHistory);

        return new ResponseDto("SUCCESS", title);

    }
    /*
    @Transactional
    public ResponseDto deleteExerciseHistoryByTitle(ServletRequest request, String title, ExerciseDto exerciseDto) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User me = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        ExerciseList exerciseList = exerciseListRepository.findAllByUserAndTitle(me,title);
        List<ExerciseHistory> exerciseHistories = exerciseHistoryRepository
                .findByExerciseListAndExerciseName(exerciseList,title);
        운동 루틴을 찾고 운동 루틴 안에 있는 여러 운동들은 exerciseHistories에 존재
        여기서 어떤 운동을 삭제할지는 QueryDsl을 사용해야함(어떤 운동인지를 먼저 찾아야 함)
    }*/
}