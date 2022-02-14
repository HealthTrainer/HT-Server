package com.healthtrainer.htserver.domain.exercise;

import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;


@Getter
@Entity
@Table(name = "e_history")
@DynamicUpdate
public class ExerciseHistory {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "e_history_id")
    private Long historyId;

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

    @ManyToOne
    @JoinColumn(name = "e_list_id")
    private ExerciseList exerciseList;

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

    public void setExerciseList(ExerciseList exerciseList) {
        this.exerciseList = exerciseList;
    }

}