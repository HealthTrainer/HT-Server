package com.healthtrainer.htserver.service.register;

import com.healthtrainer.htserver.domain.register.User;
import com.healthtrainer.htserver.domain.register.UserRepository;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.register.RegisterDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public ResponseDto signUp(RegisterDto loginRequestDto){
        userRepository.save(User.builder()
                    .password(passwordEncoder.encode(loginRequestDto.getPassword()))
                    .name(loginRequestDto.getName())
                    .roles(Collections.singletonList("ROLE_USER"))
                    .picture(loginRequestDto.getPicture())
                    .age(loginRequestDto.getAge())
                    .sex(loginRequestDto.getSex())
                    .height(loginRequestDto.getHeight())
                    .weight(loginRequestDto.getWeight())
                    .email(loginRequestDto.getEmail())
                    .profileState(loginRequestDto.getProfile_state())
                    .withdrawlState(loginRequestDto.getWithdrawl_state())
                    .build());
            return new ResponseDto("SUCCESS");
        }


    public ResponseDto emailCheck(RegisterDto registerDto){
        User user = userRepository.findAllByEmail(registerDto.getEmail());
        if(user == null){
            return new ResponseDto("SUCCESS","가입 가능한 이메일입니다.");
        }
        return new ResponseDto("FAIL", "이메일이 중복됩니다.");
    }
}