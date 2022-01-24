package com.healthtrainer.htserver.web.controller.login;

import com.healthtrainer.htserver.config.JwtAuthenticationProvider;
import com.healthtrainer.htserver.domain.register.User;
import com.healthtrainer.htserver.domain.register.UserRepository;
import com.healthtrainer.htserver.web.dto.login.LoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginApiController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @PostMapping("/auth/login")
    @ResponseBody
    public String login(@RequestBody LoginDto user) {
        User member = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(user.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        String token = jwtAuthenticationProvider.createToken(member.getUsername(), member.getRoles());

        return token;
    }
}