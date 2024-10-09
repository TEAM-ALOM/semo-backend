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
    private Long id;
    private String user_id;
    private String pw;
    private String name;
    private String nickname;
    private String role;

    public UserSignupRes toDto(User user){
        return UserSignupRes.builder()
                .id(user.getId())
                .user_id(user.getUser_id())
                .pw(user.getPw())
                .name(user.getName())
                .nickname(user.getNickname())
                .role(user.getRole())
                .build();
    }
}
