package com.healthtrainer.htserver.service.register;

import com.healthtrainer.htserver.domain.register.User;
import com.healthtrainer.htserver.domain.register.UserRepository;
import com.healthtrainer.htserver.web.dto.register.RegisterDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class ReigsterService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public String signUp(RegisterDto loginRequestDto){
        try{
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

            return "Success";
        }
        catch (Exception e){
            return "Fail1";
        }
    }


}