package com.healthtrainer.htserver.service.login;

import com.healthtrainer.htserver.domain.login.User;
import com.healthtrainer.htserver.domain.login.UserRepository;
import com.healthtrainer.htserver.web.dto.login.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final UserRepository userRepository;

    public String signUp(LoginRequestDto loginRequestDto){ // 회원가입
        userRepository.save(User.builder()
                .id(loginRequestDto.getId())
                .name(loginRequestDto.getName())
                .age(loginRequestDto.getAge())
                .sex(loginRequestDto.getSex())
                .height(loginRequestDto.getHeight())
                .weight(loginRequestDto.getWeight())
                .email(loginRequestDto.getEmail())
                .profileState(loginRequestDto.getProfileState())
                .withdrawlState(loginRequestDto.getWithdrawlState())
                .build());
        return "Success";
    }

    public String login(String id){ // 로그인
        Optional<User> user = userRepository.findById(id);
        log.info("id = {}", "input id = {}", user.get().getId(), id);
        if(user.get().getId().equals(id)){
            return "Success";
        }
        else{
            return "Fail";
        }
    }
}