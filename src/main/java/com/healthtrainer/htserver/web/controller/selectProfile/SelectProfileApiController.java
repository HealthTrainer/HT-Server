package com.healthtrainer.htserver.web.controller.selectProfile;

import com.healthtrainer.htserver.domain.register.User;
import com.healthtrainer.htserver.service.selectProfile.SelectProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SelectProfileApiController {

    private final SelectProfileService selectProfileService;

    @ResponseBody
    @GetMapping("/users/{userId}/profile/select")
    public String selectProfile(@PathVariable("userId") String userId) {
        User selectedUser = selectProfileService.selectProfile(userId);
        log.info("email: {}, selectedUserEmail = {}", userId, selectedUser.getEmail());

     if(selectedUser.getEmail().equals(userId)){
         return "Success";
     }
        return "Fail";
    }
}
