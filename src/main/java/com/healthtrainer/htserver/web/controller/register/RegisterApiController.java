package com.healthtrainer.htserver.web.controller.register;

import com.healthtrainer.htserver.service.register.RegisterService;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.register.RegisterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RegisterApiController {

    private final RegisterService registerService;

    @PostMapping("/auth/sign-up") // 회원가입 처리
    @ResponseBody
    public ResponseDto signUp(@RequestBody RegisterDto registerDto) { // 수신
        log.info("email = {}, password = {}", registerDto.getEmail(), registerDto.getPassword());
        return registerService.signUp(registerDto);
    }

    @GetMapping("/auth/email")
    @ResponseBody
    public ResponseDto emailCheck(@RequestBody RegisterDto registerDto){
        return registerService.emailCheck(registerDto);
    }
}