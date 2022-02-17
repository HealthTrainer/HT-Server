package com.healthtrainer.htserver.domain.calendar;


import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "c_history")
public class CalendarHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_history_id")
    private Long calendarHistoryId;

    @Column(name = "e_name")
    private String exerciseName;

    @Column(name = "e_type")
    private String exerciseType;

    @Column(name = "e_count")
    private Integer exerciseCount;

    @Column(name = "e_set")
    private Integer exerciseSet;

    @Column(name = "e_weight")
    private Integer exerciseWeight;

    @Column(name = "e_time")
    private Integer exerciseTime;

    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public void setExerciseCount(Integer exerciseCount) {
        this.exerciseCount = exerciseCount;
    }

    public void setExerciseSet(Integer exerciseSet) {
        this.exerciseSet = exerciseSet;
    }

    public void setExerciseWeight(Integer exerciseWeight) {
        this.exerciseWeight = exerciseWeight;
    }

    public void setExerciseTime(Integer exerciseTime) {
        this.exerciseTime = exerciseTime;
    }

    public void setColor(String color){
        this.color = color;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
