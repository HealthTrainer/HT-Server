package com.healthtrainer.htserver.web.dto.exercise;

import lombok.Getter;

@Getter
public class ExerciseDto {

    private Integer time;
    private String title;
    private String name;
    private String type;
    private Integer count;
    private Integer weight;
    private Integer set;
}