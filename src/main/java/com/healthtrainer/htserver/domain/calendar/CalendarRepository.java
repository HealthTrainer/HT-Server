package com.healthtrainer.htserver.domain.calendar;

import com.healthtrainer.htserver.domain.register.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar,Long> {

    Calendar findByDate(String date);
    List<Calendar> findAllById(Long id);
}
