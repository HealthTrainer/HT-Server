package com.healthtrainer.htserver.service.exercise;

import com.healthtrainer.htserver.config.JwtAuthenticationProvider;
import com.healthtrainer.htserver.domain.Follow.Follow;
import com.healthtrainer.htserver.domain.exercise.ExerciseHistory;
import com.healthtrainer.htserver.domain.exercise.ExerciseHistoryRepository;
import com.healthtrainer.htserver.domain.exercise.ExerciseList;
import com.healthtrainer.htserver.domain.exercise.ExerciseListRepository;
import com.healthtrainer.htserver.domain.register.User;
import com.healthtrainer.htserver.domain.register.UserRepository;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.exercise.ExerciseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ExerciseListService {

    private final ExerciseListRepository exerciseListRepository;
    private final ExerciseHistoryRepository exerciseHistoryRepository;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;

    @Transactional
    public ResponseDto addExerciseList(ServletRequest request, ExerciseDto exerciseDto){
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User me = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        ExerciseList exerciseList = new ExerciseList();
        exerciseList.seteListTime(exerciseDto.getTime());
        exerciseList.seteListTitle(exerciseDto.getTitle());
        exerciseList.setUser(me);
        exerciseListRepository.save(exerciseList);

        return new ResponseDto("SUCCESS",me.getEmail());
    }

    public ResponseDto deleteExerciseList(ServletRequest request, String title) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User me = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        ExerciseList exerciseList = exerciseListRepository.findByUserAndTitle(me,title);
        exerciseListRepository.delete(exerciseList);

        return new ResponseDto("SUCCESS",title);
    }

    public ResponseDto selectExerciseListByIdAndTitle(Long id, String title) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원 입니다. id=" + id));

        ExerciseList exerciseList = exerciseListRepository.findByUserAndTitle(user,title);

        List<ExerciseHistory> exerciseHistories = new ArrayList<>();
        exerciseHistories = exerciseHistoryRepository.findAllByExerciseList(exerciseList);

        List<ExerciseHistory> forReturn = new ArrayList<>();
        for (ExerciseHistory e : exerciseHistories) {
            ExerciseHistory temp = new ExerciseHistory();
            /* temp 객체는 반복문 안에 존재해야 함 객체의 정보가 계속 바뀌기 때문 */

            temp.setExerciseName(e.getExerciseName());
            temp.setExerciseType(e.getExerciseType());
            temp.setExerciseCount(e.getExerciseCount());
            temp.setExerciseSet(e.getExerciseSet());
            temp.setExerciseWeight(e.getExerciseWeight());

            forReturn.add(temp);
        }

        return new ResponseDto("SUCCESS",forReturn);
    }

    public ResponseDto selectAllExerciseList(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원 입니다. id=" + id));

        List<ExerciseList> exerciseLists = new ArrayList<>();
        exerciseLists = exerciseListRepository.findAllByUser(user);

        List<ExerciseHistory> exerciseHistories = new ArrayList<>();

        List<Object> forReturn = new ArrayList<>();
        for(ExerciseList e1 : exerciseLists) {
            exerciseHistories = exerciseHistoryRepository.findAllByExerciseList(e1);
            for(ExerciseHistory e2 : exerciseHistories){
                ExerciseHistory temp = new ExerciseHistory();
                /* temp 객체는 반복문 안에 존재해야 함 객체의 정보가 계속 바뀌기 때문 */
                forReturn.add(e1.getTitle());
                temp.setExerciseName(e2.getExerciseName());
                temp.setExerciseType(e2.getExerciseType());
                temp.setExerciseCount(e2.getExerciseCount());
                temp.setExerciseSet(e2.getExerciseSet());
                temp.setExerciseWeight(e2.getExerciseWeight());

                forReturn.add(temp);
            }
        }
        return new ResponseDto("SUCCESS",forReturn);
    }
}