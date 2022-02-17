package com.healthtrainer.htserver.domain.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team,Long> {
    @Query(value = "SELECT t FROM Team t WHERE t.teamName LIKE %:teamName%")
    List<Team> findAllByKeyword(@Param("teamName") String keyword);
    /*
     * 위의 형식은 List<Team> findByTeamNameContaining(String keyword)로도 구현 가능
     * t.~ 에서 ~는 객체의 속성을 넣어줘야 함
     */
}
