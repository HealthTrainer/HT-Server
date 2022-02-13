package com.healthtrainer.htserver.web.dto.exercise;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindExerciseListDto {
    private Long exerciseListId;
    private String title;
    private Integer time;
}
