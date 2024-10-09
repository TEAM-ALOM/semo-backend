package com.semo.semo.domain.user.repository;

import com.semo.semo.domain.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReposiotry extends JpaRepository<User,Long> {
}
