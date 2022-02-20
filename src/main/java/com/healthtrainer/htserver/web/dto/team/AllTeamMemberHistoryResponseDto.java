package com.healthtrainer.htserver.web.dto.team;

import com.healthtrainer.htserver.web.dto.calendar.CalendarHistoryAllResponseDto;
import com.healthtrainer.htserver.web.dto.calendar.CalendarHistoryResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AllTeamMemberHistoryResponseDto {
    private String name;
    private List<CalendarHistoryAllResponseDto> calendarHistoryAllResponseDtoList;
}
