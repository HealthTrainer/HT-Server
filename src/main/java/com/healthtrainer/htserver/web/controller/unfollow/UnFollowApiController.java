package com.healthtrainer.htserver.web.controller.unfollow;

import com.healthtrainer.htserver.service.unfollow.UnFollowService;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.follow.FollowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;

@RestController
@RequiredArgsConstructor
public class UnFollowApiController {

    private final UnFollowService unFollowService;

    @DeleteMapping("/users/{id}/follow") // id 값은 언팔로잉할 id의 값
    public ResponseDto unFollow(@PathVariable Long id, ServletRequest request){
        return unFollowService.unFollow(id,request);

    }


}
