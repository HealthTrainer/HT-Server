package com.healthtrainer.htserver.domain.calendar;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarHistoryRepository extends JpaRepository<CalendarHistory,Long> {
    List<CalendarHistory> findAllByCalendar(Calendar calendar);

}
