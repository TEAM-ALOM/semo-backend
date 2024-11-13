package com.semo.semo.domain.user.model.response;

import com.semo.semo.domain.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserSignupRes {
    private String id;
    private String name;
    private String nickname;
    private String role;

    public UserSignupRes toDto(User user){
        return UserSignupRes.builder()
                .id(user.getUserId())
                .name(user.getName())
                .nickname(user.getNickname())
                .role(user.getRole())
                .build();
    }
}
