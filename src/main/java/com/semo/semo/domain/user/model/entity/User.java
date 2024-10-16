package com.semo.semo.domain.user.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Entity
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String user_id;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String role = "user";

    @Builder
    public User(Long id,String user_id,String pw,String name,String nickname,String role){
        this.id = id;
        this.user_id = user_id;
        this.pw = pw;
        this.name = name;
        this.nickname = nickname;
        if(role != null) this.role = role;
    }
}
