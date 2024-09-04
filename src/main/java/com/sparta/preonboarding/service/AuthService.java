package com.sparta.preonboarding.service;

import com.sparta.preonboarding.dto.LoginRequestDto;
import com.sparta.preonboarding.dto.LoginResponseDto;
import com.sparta.preonboarding.dto.SignupRequestDto;
import com.sparta.preonboarding.dto.SignupResponseDto;
import com.sparta.preonboarding.model.Role;
import com.sparta.preonboarding.model.User;
import com.sparta.preonboarding.repository.UserRepository;
import com.sparta.preonboarding.filter.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = encodePassword(signupRequestDto.getPassword());
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

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        // 사용자 확인
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty() || !passwordEncoder.matches(password, userOptional.get().getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        User user = userOptional.get();

        // JWT 생성
        String accessToken = jwtUtil.createToken(user.getUsername(), Role.USER);
        String tokenWithoutBearer = accessToken.replace("Bearer ", "");

        // 응답 DTO 생성
        return new LoginResponseDto(tokenWithoutBearer);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
