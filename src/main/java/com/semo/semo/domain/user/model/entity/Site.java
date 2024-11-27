package com.semo.semo.domain.user.model.entity;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor // 기본 생성자 추가 // 모든 필드를 포함한 생성자 추가
@Builder
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255)")
    private String url;

    @Column(columnDefinition = "VARCHAR(100)")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "BIGINT")
    private Long userId;

    // 게터와 세터 메서드 생략됨 (Lombok이 자동 생성)
}
