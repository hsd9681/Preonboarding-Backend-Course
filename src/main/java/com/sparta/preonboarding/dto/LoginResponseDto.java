package com.sparta.preonboarding.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String token;

    public LoginResponseDto(String accessToken) {
        this.token = accessToken;
    }
}
