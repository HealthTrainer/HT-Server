package com.healthtrainer.htserver.domain.exercise;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseHistoryRepository extends JpaRepository<ExerciseHistory,Long> {

    List<ExerciseHistory> findAllByExerciseList(ExerciseList exerciseList);
    List<ExerciseHistory> findByExerciseListAndExerciseName(ExerciseList exerciseList, String exerciseName);

}

