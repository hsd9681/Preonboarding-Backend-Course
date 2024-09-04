package com.sparta.preonboarding.dto;

import com.sparta.preonboarding.model.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SignupResponseDto {
    private final String username;
    private final String nickname;
    private final List<AuthorityResponse> authorities;

    @Getter
    private static class AuthorityResponse {
        private final String authorityName;

        public AuthorityResponse(String authorityName) {
            this.authorityName = authorityName;
        }
    }

    public SignupResponseDto(User user) {
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.authorities = new ArrayList<>();
        // 권한을 리스트에 추가
        this.authorities.add(new AuthorityResponse(user.getAuthority()));

    }


}
