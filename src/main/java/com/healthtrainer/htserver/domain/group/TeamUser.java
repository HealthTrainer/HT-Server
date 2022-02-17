package com.healthtrainer.htserver.domain.group;

import com.healthtrainer.htserver.domain.register.User;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "team_user")
@Getter
public class TeamUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_user_id")
    private Long teamUserId;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    @Column(name = "grade")
    private String grade;
}