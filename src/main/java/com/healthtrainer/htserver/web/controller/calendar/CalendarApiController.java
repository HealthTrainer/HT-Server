package com.healthtrainer.htserver.web.controller.calendar;

import com.healthtrainer.htserver.service.calendar.CalendarService;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.calendar.CalendarHistoryRequestDto;
import com.healthtrainer.htserver.web.dto.calendar.CalendarRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;

@Controller
@RequiredArgsConstructor
public class CalendarApiController {

    private final CalendarService calendarService;

    @PostMapping("/users/calendar/{title}")
    @ResponseBody
    public ResponseDto addCalendar(ServletRequest request, @PathVariable String title,
                                   @RequestBody CalendarRequestDto calendarRequestDto){
        return calendarService.addCalendar(request, title, calendarRequestDto);
    }

    @PostMapping("/users/calendar/{title}/{exerciseId}")
    @ResponseBody
    public ResponseDto addCalendarHistory(ServletRequest request, @PathVariable String title, @PathVariable Long exerciseId,
                                   @RequestBody CalendarHistoryRequestDto calendarRequestDto){
        return calendarService.addCalendarHistory(request, title, exerciseId, calendarRequestDto);
    }

    @GetMapping("/users/calendar/{title}")
    @ResponseBody
    public ResponseDto findAllCalendarHistory(ServletRequest request, @PathVariable String title){
        return calendarService.findAllCalendarHistory(request,title);
    }

    @GetMapping("/users/calendar/time")
    @ResponseBody
    public ResponseDto findCalendarTime(ServletRequest request){
        return calendarService.findCalendarTime(request);
    }
}
