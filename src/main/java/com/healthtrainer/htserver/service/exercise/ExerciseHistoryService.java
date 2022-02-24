package com.healthtrainer.htserver.service.exercise;

import com.healthtrainer.htserver.config.JwtAuthenticationProvider;
import com.healthtrainer.htserver.domain.exercise.ExerciseHistory;
import com.healthtrainer.htserver.domain.exercise.ExerciseHistoryRepository;
import com.healthtrainer.htserver.domain.exercise.ExerciseList;
import com.healthtrainer.htserver.domain.exercise.ExerciseListRepository;
import com.healthtrainer.htserver.domain.register.User;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.exercise.ExerciseDto;
import com.healthtrainer.htserver.web.dto.exercise.FindExerciseHistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseHistoryService {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserDetailsService userDetailsService;
    private final ExerciseHistoryRepository exerciseHistoryRepository;
    private final ExerciseListRepository exerciseListRepository;

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

        List<Object> forReturn = new ArrayList<>();

        FindExerciseHistoryDto temp = new FindExerciseHistoryDto();
        temp.setExerciseListId(exerciseList.getEListId());
        temp.setTitle(exerciseList.getTitle());
        temp.setExerciseHistoryId(exerciseHistory.getHistoryId());
        temp.setExerciseName(exerciseHistory.getExerciseName());

        forReturn.add(temp);

        return new ResponseDto("SUCCESS", forReturn);

    }

    @Transactional
    public ResponseDto deleteExerciseHistory(Long exerciseId) {
        ExerciseHistory exerciseHistory = exerciseHistoryRepository.getById(exerciseId);
        exerciseHistoryRepository.delete(exerciseHistory);

        return new ResponseDto("SUCCESS",exerciseHistory.getExerciseName());
    }
}