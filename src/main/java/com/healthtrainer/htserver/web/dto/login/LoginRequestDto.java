package com.healthtrainer.htserver.web.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginRequestDto {

    private String id;
    private String name;
    private int age;
    private char sex;
    private int height;
    private int weight;
    private String email;
    private char profileState;
    private char withdrawlState;
}
