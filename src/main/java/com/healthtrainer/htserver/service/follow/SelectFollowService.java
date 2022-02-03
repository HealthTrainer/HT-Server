package com.healthtrainer.htserver.service.follow;

import com.healthtrainer.htserver.config.JwtAuthenticationProvider;
import com.healthtrainer.htserver.domain.Follow.Follow;
import com.healthtrainer.htserver.domain.Follow.FollowRepository;
import com.healthtrainer.htserver.domain.register.User;
import com.healthtrainer.htserver.domain.register.UserRepository;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SelectFollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserDetailsService userDetailsService;

    @ResponseBody
    public ResponseDto selectFollowingByMe(ServletRequest request){
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User me = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        List<Follow> followingsByMe = followRepository.findALLByfEmail(me.getEmail());
        List<String> emails = new ArrayList<>();
        for (Follow f : followingsByMe) {
            User user = f.getUser();
            emails.add(user.getEmail());
        }
        return new ResponseDto("SUCCESS",emails);
    }

    @ResponseBody
    public ResponseDto selectFollowerByMe(ServletRequest request){
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User me = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        List<Follow> followerByMe = followRepository.findALLByUser(me);
        List<String> emails = new ArrayList<>();

        for(Follow f : followerByMe){
            emails.add(f.getFEmail());
        }

        return new ResponseDto("SUCCESS",emails);
    }


    public ResponseDto selectFollowingByUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 존재하지 않습니다 id = "+id));

        List<Follow> followingsByUser = followRepository.findALLByfEmail(user.getEmail());
        List<String> emails = new ArrayList<>();
        for (Follow f : followingsByUser) {
            emails.add(f.getUser().getEmail());
        }
        return new ResponseDto("SUCCESS",emails);
    }

    public ResponseDto selectFollowerByUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 존재하지 않습니다 id = "+id));

        List<Follow> followingsByUser = followRepository.findALLByUser(user);
        List<String> emails = new ArrayList<>();
        for (Follow f : followingsByUser) {
            emails.add(f.getFEmail());
        }
        return new ResponseDto("SUCCESS",emails);

    }
}