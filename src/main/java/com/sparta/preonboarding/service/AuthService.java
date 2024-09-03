package com.sparta.preonboarding.service;

import com.sparta.preonboarding.dto.SignupRequestDto;
import com.sparta.preonboarding.dto.SignupResponseDto;
import com.sparta.preonboarding.model.Role;
import com.sparta.preonboarding.model.User;
import com.sparta.preonboarding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        String nickname = signupRequestDto.getNickname();

        String authority = Role.USER.getValue();

        User user = User.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .authority(authority).build();

        userRepository.save(user);

        return new SignupResponseDto(user);
    }

}
