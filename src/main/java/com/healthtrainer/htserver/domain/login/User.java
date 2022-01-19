package com.healthtrainer.htserver.domain.login;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "User_kmj")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "sex")
    private String sex;

    @Column(name = "height")
    private Integer height;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "email")
    private String email;

    @Column(name = "profile_state")
    private String profileState; // 프로필 공개 여부, value = Y or N이기 때문에 Data type이 char형

    @Column(name = "withdrawl_state")
    private String withdrawlState; // 탈퇴여부, 기존의 회원이 탈퇴할 경우 value = Y

    @Builder
    public User(String name, Integer age, String sex, Integer height, Integer weight, String email, String profileState, String withdrawlState) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.email = email;
        this.profileState = profileState;
        this.withdrawlState = withdrawlState;
    }

    public void update(User entity) {
        this.name = entity.getName();
        this.age = entity.getAge();
        this.sex = entity.getSex();
        this.height = entity.getHeight();
        this.weight = entity.getWeight();
        this.email = entity.getEmail();
        this.profileState = entity.getProfileState();
    }
}
