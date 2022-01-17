package com.healthtrainer.htserver.service.register;

import com.healthtrainer.htserver.domain.register.User;
import com.healthtrainer.htserver.domain.register.UserRepository;
import com.healthtrainer.htserver.web.dto.register.RegisterDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReigsterService {

    private final UserRepository userRepository;
    public String signUp(RegisterDto loginRequestDto){
        try{
            userRepository.save(User.builder()
                            .email(loginRequestDto.getEmail())
                            .password(loginRequestDto.getPassword())
                            .build()
                    );
            return "Success";
        }
        catch (Exception e){
            return "Fail";
        }
    }


}