package com.healthtrainer.htserver.domain.register;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterTest {

    @Autowired
    UserRepository userRepository;
    @Test
    public void userTest(){
        String password = "test2";
        String name = "박상권2";
        String picture = "test2";
        Integer age = 1;
        String sex = "M";
        Integer height = 1;
        Integer weight = 1;
        String email = "test2";
        String profileState = "a";
        String withdrawlState = "Y";

        userRepository.save(User.builder()
        .password(password)
        .name(name)
        .picture(picture)
        .age(age)
        .sex(sex)
        .height(height)
        .weight(weight)
        .email(email)
        .profileState(profileState)
        .withdrawlState(withdrawlState)
        .build());

        List<User> userList = userRepository.findAll();

         User user = userList.get(0);
         assertEquals(user.getEmail(), email);
         assertEquals(user.getPassword(),password);
    }
}