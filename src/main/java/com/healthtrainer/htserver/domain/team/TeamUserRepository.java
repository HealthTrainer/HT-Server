package com.healthtrainer.htserver.domain.team;

import com.healthtrainer.htserver.domain.register.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamUserRepository extends JpaRepository<TeamUser,Long> {
    List<TeamUser> findAllByTeam(Team team);

    TeamUser findByTeamAndUser(Team team, User user);

    Integer countByTeam(Team team);

    List<TeamUser> findAllByUser(User user);
}