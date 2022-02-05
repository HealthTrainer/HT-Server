package com.healthtrainer.htserver.domain.exercise;

import com.healthtrainer.htserver.domain.register.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseListRepository extends JpaRepository<ExerciseList,Long> {

    ExerciseList findByUserAndTitle(User user, String title);

    List<ExerciseList> findAllByUser(User user);

    ExerciseList findAllByUserAndTitle(User me, String title);
}