package com.healthtrainer.htserver.service.unfollow;

import com.healthtrainer.htserver.config.JwtAuthenticationProvider;
import com.healthtrainer.htserver.domain.Follow.Follow;
import com.healthtrainer.htserver.domain.Follow.FollowRepository;
import com.healthtrainer.htserver.domain.register.User;
import com.healthtrainer.htserver.domain.register.UserRepository;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class UnFollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserDetailsService userDetailsService;

    public ResponseDto unFollow(Long id, ServletRequest request){
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User fromUser = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        User toUser = userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원 입니다. id=" + id));


        Follow follow = followRepository.findByfEmailAndUser(fromUser.getEmail(),toUser);
        followRepository.delete(follow);

        Object object = toUser.getName();


        return new ResponseDto("SUCCESS",object);


    }
}