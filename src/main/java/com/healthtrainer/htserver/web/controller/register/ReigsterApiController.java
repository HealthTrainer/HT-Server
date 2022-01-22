package com.healthtrainer.htserver.web.controller.register;

import com.healthtrainer.htserver.service.register.ReigsterService;
import com.healthtrainer.htserver.web.dto.register.RegisterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReigsterApiController {

    private final ReigsterService reigsterService;

   @PostMapping("/auth/sign-up") // 회원가입 처리
   @ResponseBody
   public String signUp(@RequestBody RegisterDto registerDto){ // 수신
       log.info("email = {}, password = {}", registerDto.getEmail(),registerDto.getPassword());
       if(reigsterService.signUp(registerDto).equals("Success")){
           return "Success";
       }
        return "Fail";
   }
}