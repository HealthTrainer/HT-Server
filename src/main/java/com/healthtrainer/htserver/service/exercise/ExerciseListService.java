package com.healthtrainer.htserver.service.exercise;

import com.healthtrainer.htserver.config.JwtAuthenticationProvider;
import com.healthtrainer.htserver.domain.exercise.ExerciseHistory;
import com.healthtrainer.htserver.domain.exercise.ExerciseHistoryRepository;
import com.healthtrainer.htserver.domain.exercise.ExerciseList;
import com.healthtrainer.htserver.domain.exercise.ExerciseListRepository;
import com.healthtrainer.htserver.domain.register.User;
import com.healthtrainer.htserver.domain.register.UserRepository;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.exercise.ExerciseDto;
import com.healthtrainer.htserver.web.dto.exercise.FindExerciseListDto;
import com.healthtrainer.htserver.web.dto.exercise.PutExerciseListRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
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
        // 객체 생성 시점에 id 값도 같이 생성
        exerciseList.seteListTime(exerciseDto.getTime());
        exerciseList.seteListTitle(exerciseDto.getTitle());
        exerciseList.setUser(me);
        exerciseListRepository.save(exerciseList);

        return new ResponseDto("SUCCESS",exerciseList.getEListId());
    }

    public ResponseDto deleteExerciseList(ServletRequest request, String title) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User me = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        ExerciseList exerciseList = exerciseListRepository.findByUserAndTitle(me,title);
        exerciseListRepository.delete(exerciseList);

        return new ResponseDto("SUCCESS",exerciseList.getEListId());
    }

    public ResponseDto selectExerciseListByIdAndTitle(Long id, String title) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원 입니다. id=" + id));

        ExerciseList exerciseList = exerciseListRepository.findByUserAndTitle(user,title);

        List<ExerciseHistory> exerciseHistories = new ArrayList<>();
        exerciseHistories = exerciseHistoryRepository.findAllByExerciseList(exerciseList);

        List<Object> forReturn = new ArrayList<>();
        for (ExerciseHistory e : exerciseHistories) {
            ExerciseDto temp = new ExerciseDto();
            /* temp 객체는 반복문 안에 존재해야 함 객체의 정보가 계속 바뀌기 때문 */
            temp.setTitle(exerciseList.getTitle());
            temp.setTime(exerciseList.getEListTime());
            temp.setExerciseHistoryId(e.getHistoryId());
            temp.setCount(e.getExerciseCount());
            temp.setSet(e.getExerciseSet());
            temp.setType(e.getExerciseType());
            temp.setExerciseListId(exerciseList.getEListId());
            temp.setName(e.getExerciseName());
            temp.setWeight(e.getExerciseWeight());

            forReturn.add(temp);
        }

        return new ResponseDto("SUCCESS",forReturn);
    }


    public ResponseDto selectAllExerciseList(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원 입니다. id=" + id));

        List<ExerciseList> exerciseLists = new ArrayList<>();
        exerciseLists = exerciseListRepository.findAllByUser(user);

        List<Object> forReturn = new ArrayList<>();

        for(ExerciseList e1 : exerciseLists){
            FindExerciseListDto temp = new FindExerciseListDto();
            temp.setExerciseListId(e1.getEListId());
            temp.setTime(e1.getEListTime());
            temp.setTitle(e1.getTitle());

            forReturn.add(temp);

        }
        return new ResponseDto("SUCCESS",forReturn);
    }

    public ResponseDto changeExerciseList(ServletRequest request, String title,
                                          PutExerciseListRequestDto exerciseRequestDto) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User me = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        ExerciseList exerciseList = exerciseListRepository.findByUserAndTitle(me,title);

        if(!exerciseList.getTitle().equals(exerciseRequestDto.getTitle())){
            exerciseList.seteListTitle(exerciseRequestDto.getTitle());
            exerciseListRepository.save(exerciseList);
        }

        if(!exerciseList.getEListTime().equals(exerciseRequestDto.getTitle())){
            exerciseList.seteListTime(exerciseRequestDto.getTime());
            exerciseListRepository.save(exerciseList);
        }

        return new ResponseDto("SUCCESS",exerciseList.getEListId());
    }
}