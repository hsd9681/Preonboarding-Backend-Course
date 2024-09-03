package com.sparta.preonboarding.controller;


import com.sparta.preonboarding.dto.LoginRequestDto;
import com.sparta.preonboarding.dto.LoginResponseDto;
import com.sparta.preonboarding.dto.SignupRequestDto;
import com.sparta.preonboarding.dto.SignupResponseDto;
import com.sparta.preonboarding.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    @PostMapping("signup")
    public SignupResponseDto signup(@RequestBody SignupRequestDto signupRequestDto) {
        return authService.signup(signupRequestDto);
    }

    @PostMapping("login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }

}
