package com.semo.semo.domain.user.model.response;

import com.semo.semo.domain.user.model.entity.Site;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SiteRes {
    private Long id;
    private String url;
    private String title;
    private String description;
    private Long userId;

    // Site 엔티티를 SiteRes DTO로 변환하는 메서드
    public static SiteRes toDto(Site site) {
        return SiteRes.builder()
                .id(site.getId())
                .url(site.getUrl())
                .title(site.getTitle())
                .description(site.getDescription())
                .userId(site.getUserId())
                .build();
    }
}
