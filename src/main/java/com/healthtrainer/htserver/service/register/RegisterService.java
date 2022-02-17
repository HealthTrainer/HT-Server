package com.healthtrainer.htserver.service.register;

import com.healthtrainer.htserver.domain.register.User;
import com.healthtrainer.htserver.domain.register.UserRepository;
import com.healthtrainer.htserver.service.storage.StorageService;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.login.UserResponseDto;
import com.healthtrainer.htserver.web.dto.register.RegisterDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@AllArgsConstructor
public class RegisterService {

    @Qualifier("FileStorageService")
    private final StorageService storageService;
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseDto signUp(RegisterDto loginRequestDto, MultipartFile file) throws Exception {
        User user = userRepository.save(User.builder()
                    .password(passwordEncoder.encode(loginRequestDto.getPassword()))
                    .name(loginRequestDto.getName())
                    .roles(Collections.singletonList("ROLE_USER"))
                    .picture(null)
                    .age(loginRequestDto.getAge())
                    .sex(loginRequestDto.getSex())
                    .height(loginRequestDto.getHeight())
                    .weight(loginRequestDto.getWeight())
                    .email(loginRequestDto.getEmail())
                    .profileState(loginRequestDto.getProfile_state())
                    .withdrawlState(loginRequestDto.getWithdrawl_state())
                    .build());

        if (file != null) {
            String path = "/profile_" + user.getId();
            String storeUrl = storageService.store(path, file);
            user.setPicture(storeUrl);
        }

        UserResponseDto responseDto = new UserResponseDto(user);
        return new ResponseDto("SUCCESS", responseDto);
        }


    public ResponseDto emailCheck(RegisterDto registerDto){
        User user = userRepository.findAllByEmail(registerDto.getEmail());
        if(user == null){
            return new ResponseDto("SUCCESS","가입 가능한 이메일입니다.");
        }
        return new ResponseDto("FAIL", "이메일이 중복됩니다.");
    }
}