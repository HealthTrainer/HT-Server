package com.healthtrainer.htserver.domain.Follow;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow,Long> {
    /*
    팔로잉을 한 사람들의 Repository
    ex) A가 B를 팔로잉하는 경우 A가 이 Repository에 저장
    */
}
