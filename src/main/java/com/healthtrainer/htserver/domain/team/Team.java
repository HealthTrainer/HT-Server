
package com.healthtrainer.htserver.domain.team;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team")
@Getter
public class Team{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "name")
    private String teamName;

    @Column(name = "number")
    private Integer teamNumber;

    @Column(name = "state")
    private String teamState;

    @Column(name = "password")
    private String teamPassword;

    @OneToMany(mappedBy = "team")
    List<TeamUser> teamUsers = new ArrayList<>();

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setTeamNumber(Integer teamNumber) {
        this.teamNumber = teamNumber;
    }

    public void setTeamState(String teamState) {
        this.teamState = teamState;
    }

    public void setTeamPassword(String teamPassword) {
        this.teamPassword = teamPassword;
    }

}