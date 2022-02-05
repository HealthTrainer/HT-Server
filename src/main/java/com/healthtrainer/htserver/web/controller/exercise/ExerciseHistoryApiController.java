package com.healthtrainer.htserver.web.controller.exercise;

import com.healthtrainer.htserver.domain.exercise.ExerciseHistory;
import com.healthtrainer.htserver.service.exercise.ExerciseHistoryService;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.exercise.ExerciseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;

@Controller
@RequiredArgsConstructor
public class ExerciseHistoryApiController {

    private final ExerciseHistoryService exerciseHistoryService;

    @PostMapping("/users/workout-list/workout/{title}")
    @ResponseBody
    public ResponseDto addExerciseHistoryByTitle(ServletRequest request, @PathVariable String title,
                                                 @RequestBody ExerciseDto exerciseDto){
        return exerciseHistoryService.addExerciseHistoryByTitle(request, title, exerciseDto);
    }

    /*
    @DeleteMapping("/users/workout-list/workout/{title}")
    @ResponseBody
    public ResponseDto deleteExerciseHistoryByTitle(ServletRequest request, @PathVariable String title,
                                                    @RequestBody ExerciseDto exerciseDto){
        return exerciseHistoryService.deleteExerciseHistoryByTitle(request, title, exerciseDto);
    }

     */
}