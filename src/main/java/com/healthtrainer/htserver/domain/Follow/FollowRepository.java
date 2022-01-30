package com.healthtrainer.htserver.domain.Follow;

import com.healthtrainer.htserver.domain.register.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow,Long> {

    Follow findByfEmailAndUser(String fEmail, User user);
    /* JpaRepository를 메소드를 사용할 때는 DB의 물리명이 아닌 자바의 변수, 클래스명으로 사용,
       자바의 변수, 클래스명이 물리 DB에 매칭
    */

    List<Follow> findALLByfEmail(String email); // 나의 팔로잉 목록을 보고싶을 때 사용
    List<Follow> findALLByUser(User user); // 나의 팔로워 목록을 보고싶을 때 사용
}