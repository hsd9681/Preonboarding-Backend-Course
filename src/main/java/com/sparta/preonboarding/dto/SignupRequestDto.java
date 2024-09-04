package com.sparta.preonboarding.dto;

import com.sparta.preonboarding.model.User;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String nickname;

    public void setUsername(String testuser) {
        this.username = testuser;
    }

    public void setPassword(String password) {
        this.password = "password";
    }

    public void setNickname(String testUser) {
        this.nickname = testUser;
    }
}
