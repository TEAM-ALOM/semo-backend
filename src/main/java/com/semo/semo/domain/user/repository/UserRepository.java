package com.semo.semo.domain.user.repository;

import com.semo.semo.domain.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT count (u.userId) > 0 FROM User u  WHERE u.userId = :userId")
    boolean existsByUser_id(String userId);

    @Query("SELECT u FROM User u WHERE u.userId = :userId")
    Optional<User> findByUserId(@Param("userId") String userId);
}
