package com.healthtrainer.htserver.domain.team;

import com.healthtrainer.htserver.domain.register.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "team_user")
@NoArgsConstructor
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

    @Builder
    public TeamUser(Team team, User user, String grade){
        this.team = team;
        this.user = user;
        this.grade = grade;
    }
}