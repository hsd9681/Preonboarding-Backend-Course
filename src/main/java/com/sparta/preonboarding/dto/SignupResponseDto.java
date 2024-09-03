package com.sparta.preonboarding.dto;

import com.sparta.preonboarding.model.Role;
import com.sparta.preonboarding.model.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SignupResponseDto {
    private final String username;
    private final String nickname;
    private List<AuthorityResponse> authorities;

    @Getter
    private class AuthorityResponse {
        private String authorityName;

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
