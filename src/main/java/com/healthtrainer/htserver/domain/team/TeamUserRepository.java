package com.healthtrainer.htserver.domain.team;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamUserRepository extends JpaRepository<TeamUser,Long> {
    List<TeamUser> findByTeam(Team team);
    Integer countByTeam(Team team);

}