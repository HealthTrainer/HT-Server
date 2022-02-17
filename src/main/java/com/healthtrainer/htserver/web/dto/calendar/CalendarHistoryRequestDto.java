package com.healthtrainer.htserver.web.dto.calendar;

import lombok.Getter;

@Getter
public class CalendarHistoryRequestDto {
    private String name;
    private String type;
    private Integer count;
    private Integer set;
    private Integer weight;
    private Integer time; // 각 운동의 소요 시간
    private String date; // 운동한 날짜
}
