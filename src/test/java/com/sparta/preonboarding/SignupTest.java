package com.sparta.preonboarding;

import com.sparta.preonboarding.dto.SignupRequestDto;
import com.sparta.preonboarding.dto.SignupResponseDto;
import com.sparta.preonboarding.model.User;
import com.sparta.preonboarding.repository.UserRepository;
import com.sparta.preonboarding.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class SignupTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(passwordEncoder.encode(any(String.class))).thenAnswer(invocation -> {
            String rawPassword = invocation.getArgument(0);
            return new BCryptPasswordEncoder().encode(rawPassword); // 실제로 BCryptPasswordEncoder를 사용하여 인코딩
        });
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void testSignup() {
        // Given
        SignupRequestDto signupRequestDto = new SignupRequestDto();
        signupRequestDto.setUsername("testuser");
        signupRequestDto.setPassword("password");
        signupRequestDto.setNickname("TestUser");

        User user = User.builder()
                .username(signupRequestDto.getUsername())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .nickname(signupRequestDto.getNickname())
                .authority("ROLE_USER")
                .build();

        // When
        when(userRepository.save(any(User.class))).thenReturn(user);

        SignupResponseDto responseDto = authService.signup(signupRequestDto);

        // Then
        assertEquals(signupRequestDto.getUsername(), responseDto.getUsername());
        assertEquals(signupRequestDto.getNickname(), responseDto.getNickname());

    }
}
