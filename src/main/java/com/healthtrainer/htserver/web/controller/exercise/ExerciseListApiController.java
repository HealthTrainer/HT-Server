package com.healthtrainer.htserver.web.controller.exercise;

import com.healthtrainer.htserver.service.exercise.ExerciseListService;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.exercise.ExerciseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;

@Controller
@RequiredArgsConstructor
public class ExerciseListApiController {

    private final ExerciseListService exerciseListService;

    @PostMapping("/users/workout-list") // 운동리스트 생성
    @ResponseBody
    public ResponseDto addExerciseList(ServletRequest request, @RequestBody ExerciseDto exerciseDto){
        return exerciseListService.addExerciseList(request, exerciseDto);
    }

    @DeleteMapping("/users/workout-list/{title}") // 운동리스트 삭제
    @ResponseBody
    public ResponseDto deleteExerciseList(ServletRequest request, @PathVariable String title){
        return exerciseListService.deleteExerciseList(request, title);
    }


    @GetMapping("/users/{id}/workout-list/{title}") // 운동리스트 조회
    @ResponseBody
    public ResponseDto selectExerciseListByTitle(@PathVariable Long id, @PathVariable String title){
        return exerciseListService.selectExerciseListByIdAndTitle(id, title);
    }

    @GetMapping("/users/{id}/workout-list/all") // 운동리스트 전부 조회
    @ResponseBody
    public ResponseDto selectAllExerciseListB(@PathVariable Long id){
        return exerciseListService.selectAllExerciseList(id);
    }


}