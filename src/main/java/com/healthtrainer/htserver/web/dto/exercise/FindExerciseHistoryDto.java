package com.healthtrainer.htserver.web.dto.exercise;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindExerciseHistoryDto {
    private Long exerciseListId;
    private String title;
    private Long exerciseHistoryId;
    private String exerciseName;
}
