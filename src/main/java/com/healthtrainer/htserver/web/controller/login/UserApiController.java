package com.healthtrainer.htserver.web.controller.login;

import com.healthtrainer.htserver.service.login.UserService;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.login.UserCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    //유저 프로필 정보 설정 및 수정
    @PostMapping("/users/profile/me")
    public ResponseDto createUser(ServletRequest request, @RequestBody UserCreateRequestDto requestDto) {
        return userService.createUser(request, requestDto);
    }

    //타 유저 프로필 정보 요청
    @GetMapping("/users/{userId}/profile")
    public ResponseDto findUser(@PathVariable Long userId) {
        return userService.findUser(userId);
    }


    //내 프로필 정보 요청
    @GetMapping("/users/profile/me")
    public ResponseDto findMe(ServletRequest request) {
        return userService.findMe(request);
    }
}