package com.healthtrainer.htserver.web.controller.login;

import com.healthtrainer.htserver.service.login.LoginService;
import com.healthtrainer.htserver.web.dto.login.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
@Slf4j
public class LoginApiController {

    private final LoginService loginService;

    @PostMapping("/sign-up") // 회원가입
    public ResponseEntity signUp(@RequestBody LoginRequestDto loginRequestDto) {
        log.info("id = {}, name = {}, age = {}", "sex = {}", "height = {}", "weight = {}", "email = {}",
                "profile_state = {}", "withdrawl_state = {}",
                loginRequestDto.getId(),
                loginRequestDto.getName(),
                loginRequestDto.getAge(),
                loginRequestDto.getSex(),
                loginRequestDto.getHeight(),
                loginRequestDto.getWeight(),
                loginRequestDto.getEmail(),
                loginRequestDto.getProfileState(),
                loginRequestDto.getWithdrawlState());
        if(loginService.signUp(loginRequestDto).equals("Success")) {
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login") // 로그인
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto){
        log.info("id = {}", loginRequestDto.getId());
        if(loginService.login(loginRequestDto.getId()).equals("Success")) {
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}