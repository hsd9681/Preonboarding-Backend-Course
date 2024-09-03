package com.sparta.preonboarding.dto;

import com.sparta.preonboarding.model.User;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String nickname;

}
