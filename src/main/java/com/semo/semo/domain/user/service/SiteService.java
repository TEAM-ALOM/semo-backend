package com.semo.semo.domain.user.service;

import com.semo.semo.domain.user.model.entity.Site;
import com.semo.semo.domain.user.repository.SiteRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SiteService {

    private final SiteRepository siteRepository;
    //레포지 받아오기

    //모든 사이트 보기
    public List<Site> getAllSites() {
        return siteRepository.findAll();
    }
    //id로 사이트 찾기 필요한가??
    public Site getSiteById(Long id) {
        return siteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Site not found"));
    }

    //사이트 저장하기
    public Site saveSite(Site site) {
        return siteRepository.save(site);
    }
    //사이트 수정하기
    public Site updateSite(Long id, Site updatedSite) {
        Site existingSite = siteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Site not found"));

        if (!existingSite.getUserId().equals(updatedSite.getUserId())) {
            throw new RuntimeException("You are not authorized to modify this site");
        }

        Site newSite = Site.builder()
                .id(existingSite.getId())
                .url(updatedSite.getUrl())
                .title(updatedSite.getTitle())
                .description(updatedSite.getDescription())
                .userId(existingSite.getUserId()) // User ID 변경 방지
                .build();

        return siteRepository.save(newSite);
    }

    //사이트 삭제하기
    public void deleteSite(Long id) {
        siteRepository.deleteById(id);
    }
}
