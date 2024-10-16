package com.semo.semo.domain.user.repository;

import com.semo.semo.domain.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserReposiotry extends JpaRepository<User,Long> {
    @Query("SELECT count (u.user_id) > 0 FROM User u  WHERE u.user_id = :userId")
    boolean existsByUser_id(String userId);
}
