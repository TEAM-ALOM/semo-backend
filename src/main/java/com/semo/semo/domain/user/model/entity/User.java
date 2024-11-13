package com.semo.semo.domain.user.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;


@Entity
@NoArgsConstructor
@Getter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar", length = 30)
    private String userId;

    @Column(nullable = false, columnDefinition = "varchar", length = 15)
    private String name;

    @Column(nullable = false, columnDefinition = "varchar", length = 30)
    private String nickname;

    @Column(nullable = false, columnDefinition = "varchar", length = 30)
    private String role = "ROLE_USER";

    @Column(name = "refresh_token", columnDefinition = "text")
    private String refreshToken;

    @Column(name="created_at", columnDefinition = "timestamp", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public User(Long id, String userId, String name,String nickname,String role, String refreshToken, LocalDateTime createdAt){
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.nickname = nickname;
        if(role != null) this.role = role;
        this.refreshToken = refreshToken;
        this.createdAt = createdAt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
