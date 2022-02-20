package com.healthtrainer.htserver.web.dto.exercise;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutExerciseListRequestDto {
    private String title;
    private Integer time;
}
