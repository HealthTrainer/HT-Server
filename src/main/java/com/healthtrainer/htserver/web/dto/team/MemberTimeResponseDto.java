package com.healthtrainer.htserver.web.dto.team;

import com.healthtrainer.htserver.web.dto.calendar.CalendarTimeResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemberTimeResponseDto {
    private String name;
    private List<CalendarTimeResponseDto> calendarTimeResponseDtoList;
}
