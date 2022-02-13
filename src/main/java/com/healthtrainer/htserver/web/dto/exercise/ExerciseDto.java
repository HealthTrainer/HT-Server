package com.healthtrainer.htserver.web.dto.exercise;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseDto {

    private Long exerciseListId;
    private Long exerciseHistoryId;
    private Integer time;
    private String title;
    private String name;
    private String type;
    private Integer count;
    private Integer weight;
    private Integer set;


}