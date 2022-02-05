package com.healthtrainer.htserver.domain.exercise;

import com.healthtrainer.htserver.domain.Follow.Follow;
import com.healthtrainer.htserver.domain.register.User;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "e_list")
@Entity
@EntityListeners(AuditingEntityListener.class) // @CreatedDate를 사용하기 위한 어노테이션
public class ExerciseList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "e_list_id")
    private Long eListId;

    @Column(name = "e_list_date") // 운동리스트 생성 날짜
    @CreatedDate
    private LocalDateTime eListDate;

    @Column(name = "e_list_title")
    private String title; // 운동리스트 타이틀(제목), JPA 메소드를 편리하게 사용하기 위해서 "title"이라고 변수명을 바꿨습니다.

    @Column(name = "e_list_time") // 운동리스트에 운동을 다 할때 소요 시간
    private Integer eListTime;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    public void seteListTitle(String title) {
        this.title = title;
    }

    public void seteListTime(Integer time)
    {
        this.eListTime = time;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
