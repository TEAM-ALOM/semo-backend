package com.semo.semo.domain.user.model.request;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class UserSignupReq {
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String role;
}
