package com.sparta.preonboarding;

import com.sparta.preonboarding.filter.JwtUtil;
import com.sparta.preonboarding.model.Role;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {

    private JwtUtil jwtUtil;

    @Value("${jwt.secret.key}")
    private String secretKey = "7Iqk7YyM66W07YOA7L2U65Sp7YG065+9U3ByaW5n6rCV7J2Y7Yqc7YSw7LWc7JuQ67mI7J6F64uI64ukLg\\=\\="; // 테스트를 위한 비밀키

    @BeforeEach
    public void setUp() {
        jwtUtil = new JwtUtil();
        jwtUtil.setSecretKey(secretKey); // secretKey를 설정하는 메소드
        jwtUtil.init();
    }

    @Test
    public void testCreateToken() {
        String username = "testuser";
        Role role = Role.USER;

        String token = jwtUtil.createToken(username, role);

        assertNotNull(token, "Token should not be null");
        assertTrue(token.startsWith("Bearer "), "Token should start with 'Bearer '");
    }

    @Test
    public void testValidateToken() {
        String username = "testuser";
        Role role = Role.USER;

        String token = jwtUtil.createToken(username, role);
        boolean isValid = jwtUtil.validateToken(token);

        assertTrue(isValid, "Token should be valid");
    }

    @Test
    public void testGetUserInfoFromToken() {
        String username = "testuser";
        Role role = Role.USER;

        String token = jwtUtil.createToken(username, role);
        Claims claims = jwtUtil.getUserInfoFromToken(token); // Claims를 직접 가져옵니다.

        assertEquals(username, claims.getSubject(), "Username in claims should match the expected");
        assertEquals(role.getValue(), claims.get("role"), "Role in claims should match the expected");
    }

    @Test
    public void testResolveToken() {
        String token = "Bearer some_jwt_token";
        String resolvedToken = jwtUtil.resolveToken(token);

        assertEquals("some_jwt_token", resolvedToken, "Resolved token should match the expected value");
    }
}
