package com.healthtrainer.htserver.domain.Follow;

import com.healthtrainer.htserver.domain.register.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow,Long> {
    /*
        FollowRepository에는 follow 객체가 들어옴, follow 객체에 이미 팔로워와 팔로잉 정보가 존재
    */

    Follow findByfEmailAndUser(String fEmail, User user);




}
