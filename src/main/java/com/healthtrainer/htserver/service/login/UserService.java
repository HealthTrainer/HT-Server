package com.healthtrainer.htserver.service.login;

import com.healthtrainer.htserver.domain.login.User;
import com.healthtrainer.htserver.domain.login.UserRepository;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.login.UserCreateRequestDto;
import com.healthtrainer.htserver.web.dto.login.UserFindResponseDto;
import com.healthtrainer.htserver.web.dto.login.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public ResponseDto createUser(Long userId, UserCreateRequestDto requestDto) {

        User user1 = userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원 입니다. id=" + userId));

        User user2 = requestDto.toEntity();
        user1.update(user2);
        UserResponseDto responseDto = new UserResponseDto(user1);
        return new ResponseDto("SUCCESS", responseDto);
    }


    public ResponseDto findUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원 입니다. id=" + userId));

        UserFindResponseDto responseDto = new UserFindResponseDto(user);
        return new ResponseDto("SUCCESS", responseDto);
    }
}
