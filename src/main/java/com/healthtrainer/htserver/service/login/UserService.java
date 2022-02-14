package com.healthtrainer.htserver.service.login;

import com.healthtrainer.htserver.config.CustomUserDetailService;
import com.healthtrainer.htserver.config.JwtAuthenticationProvider;
import com.healthtrainer.htserver.domain.register.User;
import com.healthtrainer.htserver.domain.register.UserRepository;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.login.UserCreateRequestDto;
import com.healthtrainer.htserver.web.dto.login.UserFindResponseDto;
import com.healthtrainer.htserver.web.dto.login.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final CustomUserDetailService userDetailService;

    @Transactional
    public ResponseDto createUser(ServletRequest request, UserCreateRequestDto requestDto) {

        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User user = (User) userDetailService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        if (user == null) {
            return new ResponseDto("FAIL");
        } else {
            User user1 = userRepository.findById(user.getId())
                    .orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원 입니다. id=" + user.getId()));

            User user2 = requestDto.toEntity();

            user1.update(user2);
            UserResponseDto responseDto = new UserResponseDto(user1);
            return new ResponseDto("SUCCESS", responseDto);
        }
    }

    public ResponseDto findUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원 입니다. id=" + userId));

        UserFindResponseDto responseDto = new UserFindResponseDto(user);
        return new ResponseDto("SUCCESS", responseDto);
    }

    public ResponseDto findMe(ServletRequest request) {

        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User user = (User) userDetailService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        if (user == null) {
            return new ResponseDto("FAIL");
        } else {
            User me = userRepository.findById(user.getId())
                    .orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원 입니다. id=" + user.getId()));

            UserFindResponseDto responseDto = new UserFindResponseDto(me);
            return new ResponseDto("SUCCESS", responseDto);
        }
    }

    public ResponseDto check(ServletRequest request){
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User user = (User) userDetailService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        return new ResponseDto("SUCCESS",user.getId());
    }
}