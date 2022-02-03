package com.healthtrainer.htserver.web.controller.follow;

import com.healthtrainer.htserver.service.follow.SelectFollowService;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.follow.FollowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;

@RequiredArgsConstructor
@RestController
public class SelectFollowApiController {

    private final SelectFollowService selectFollowService;

    @GetMapping("/users/follow/me")
    public ResponseDto selectFollowingByMe(ServletRequest request) {

        return selectFollowService.selectFollowingByMe(request);
    }

    @GetMapping("/users/follower/me")
    public ResponseDto selectFollowerByMe(ServletRequest request) {

        return selectFollowService.selectFollowerByMe(request);
    }

    @GetMapping("/users/{id}/follow")
    public ResponseDto selectFollowingByUser(@PathVariable Long id){
        return selectFollowService.selectFollowingByUser(id);
    }

    @GetMapping("/users/{id}/follower")
    public ResponseDto selectFollowerByUser(@PathVariable Long id){
        return selectFollowService.selectFollowerByUser(id);
    }
}
