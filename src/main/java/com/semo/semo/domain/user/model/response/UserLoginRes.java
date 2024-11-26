package com.semo.semo.domain.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class UserLoginRes {
    private String id;
    private String nickname;
}
