package com.healthtrainer.htserver.web.dto.calendar;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CalendarRequestDto {
    private String date; // 운동한 날짜
    private Integer time; // 운동한 전체 시간
}
