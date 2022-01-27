package com.healthtrainer.htserver.domain.Follow;

import com.healthtrainer.htserver.domain.register.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "follow")
@Entity
public class Follow { // 연관관계 중 주인

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_id")
    private Long id;
     /*
        Following 테이블에 대한 인덱스 번호, 이렇게 따로 인덱스 번호를 지정하면 복합키를 지정할 필요가 없으며
        어떤 테이블에 종속 되어있는 키가 아니라서 프로그램의 기능이 추가 및 삭제 될시에 대처가 유연함
     */

    @Column(name = "f_email") // 밑의 사용자(일반 사용자)를 팔로잉한 사람의 이메일
    private String following_email;

    @ManyToOne
    @JoinColumn(name = "id") // 일반 사용자
    private User user;

    public void setUser(User user){
        this.user = user;
    }

    public void setFollowing_email(String following_email){
        this.following_email = following_email;
    }

}
