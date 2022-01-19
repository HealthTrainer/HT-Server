package com.healthtrainer.htserver.web.controller.login;

import com.healthtrainer.htserver.service.login.UserService;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.login.UserCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    //유저 프로필 정보 설정 및 수정
    @PostMapping("/users/{userId}/profile")
    public ResponseDto createUser(@PathVariable Long userId, @RequestBody UserCreateRequestDto requestDto) {
        return userService.createUser(userId, requestDto);
    }

    //유저 프로필 정보 요청
    @GetMapping("/users/{userId}/profile")
    public ResponseDto findUser(@PathVariable Long userId) {
        return userService.findUser(userId);
    }

}
