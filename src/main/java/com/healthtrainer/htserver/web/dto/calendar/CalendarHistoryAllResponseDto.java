package com.healthtrainer.htserver.web.dto.calendar;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CalendarHistoryAllResponseDto {
    private String date;
    private List<CalendarHistoryResponseDto> calendarHistoryResponseDtos;
    private String color;
}
