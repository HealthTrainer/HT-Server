package com.healthtrainer.htserver.service.follow;

import com.healthtrainer.htserver.domain.Follow.Follow;
import com.healthtrainer.htserver.domain.Follow.FollowRepository;
import com.healthtrainer.htserver.domain.register.User;
import com.healthtrainer.htserver.domain.register.UserRepository;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.follow.FollowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Transactional
    public ResponseDto follow(Long id, FollowDto followDto){
        Follow following = new Follow();
        User user = userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원 입니다. id=" + id));

        following.setFollowing_email(followDto.getEmail());
        following.setUser(user);
        followRepository.save(following); // 팔로잉을 한 사람이 저장

        return new ResponseDto("SUCCESS",followDto);
    }
}
