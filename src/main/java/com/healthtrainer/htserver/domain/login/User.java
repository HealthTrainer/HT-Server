package com.healthtrainer.htserver.domain.login;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "sex")
    private char sex;

    @Column(name = "height")
    private int height;

    @Column(name = "weight")
    private int weight;

    @Column(name = "email")
    private String email;

    @Column(name = "profile_state")
    private char profileState; // 프로필 공개 여부, value = Y or N이기 때문에 Data type이 char형

    @Column(name = "withdrawl_state")
    private char withdrawlState; // 탈퇴여부, 기존의 회원이 탈퇴할 경우 value = Y
}
