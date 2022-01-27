package com.healthtrainer.htserver.web.controller.follow;

import com.healthtrainer.htserver.service.follow.FollowService;
import com.healthtrainer.htserver.web.dto.ResponseDto;
import com.healthtrainer.htserver.web.dto.follow.FollowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class FollowApiController {

    private final FollowService followService;


    @PostMapping("/users/{id}/follow")
    @ResponseBody
    public ResponseDto follow(@PathVariable Long id, @RequestBody FollowDto followDto){
        return followService.follow(id,followDto);
        // {id}에 들어오는 값은 팔로잉을 받는 사람의 id, RequestBody에 들어오는 값들은 팔로잉을 하는 사람의 데이터
    }
}
