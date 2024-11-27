package com.semo.semo.domain.user.model.request;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class UserSignupReq {
    private String id;
    private String pw;
    private String name;
    private String nickname;

}
