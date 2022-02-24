package com.healthtrainer.htserver.web.controller.register;

import com.healthtrainer.htserver.service.register.RegisterService;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.register.RegisterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RegisterApiController {

    private final RegisterService registerService;

    @PostMapping("/auth/sign-up") // 회원가입 처리
    @ResponseBody
    public ResponseDto signUp(@ModelAttribute RegisterDto registerDto, @RequestParam(value = "file", required = false) MultipartFile file) throws Exception { // 수신
        log.info("email = {}, password = {}", registerDto.getEmail(), registerDto.getPassword());
        return registerService.signUp(registerDto, file);
    }

    @GetMapping("/auth/email")
    @ResponseBody
    public ResponseDto emailCheck(@RequestBody RegisterDto registerDto){
        return registerService.emailCheck(registerDto);
    }

    @DeleteMapping("/auth/sign-out")
    @ResponseBody
    public ResponseDto signOut(ServletRequest request){
        return registerService.signOut(request);
    }
}