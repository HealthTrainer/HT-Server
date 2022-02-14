package com.healthtrainer.htserver.web.dto.calendar;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CalendarHistoryResponseDto {
    private String name;
    private String type;
    private Integer counter;
    private Integer set;
    private Integer time;

}
