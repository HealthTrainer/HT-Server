package com.healthtrainer.htserver.domain.calendar;

import com.healthtrainer.htserver.domain.exercise.ExerciseHistory;
import com.healthtrainer.htserver.domain.exercise.ExerciseList;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "calendar")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_id")
    private Long calendarId;

    @Column(name = "date")
    private String date; // 운동한 날짜

    @Column(name = "time")
    private Integer time; // 운동한 시간

    @Column(name = "color")
    private String color; // 오늘의 color

    @Column(name = "userId")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "e_list_id")
    private ExerciseList exerciseList;

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(Integer time){
        this.time = time;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setExerciseList(ExerciseList exerciseList) {
        this.exerciseList = exerciseList;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
