package com.semo.semo.domain.user.service;

import com.semo.semo.domain.user.model.entity.Site;
import com.semo.semo.domain.user.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiteService {

    private final SiteRepository siteRepository;
    //레포지 받아오기
    @Autowired
    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }
    //모든 사이트 보기
    public List<Site> getAllSites() {
        return siteRepository.findAll();
    }
    //id로 사이트 찾기 필요한가??
    public Optional<Site> getSiteById(Long id) {
        return siteRepository.findById(id);
    }
    //사이트 저장하기
    public Site saveSite(Site site) {
        return siteRepository.save(site);
    }
    //사이트 수정하기
    public Site updateSite(Long id, Site updatedSite) {
        return siteRepository.findById(id)//id로 사이트 찾고 수정하기 + 다른사람이 수정하는거 막아야 하나?
                .map(site -> {
                    site.setUrl(updatedSite.getUrl());
                    site.setTitle(updatedSite.getTitle());
                    site.setDescription(updatedSite.getDescription());
                    /*site.setUserId(updatedSite.getUserId());*/
                    return siteRepository.save(site);
                })
                .orElseThrow(() -> new RuntimeException("Site not found"));//못찾으면
    }
    //사이트 삭제하기
    public void deleteSite(Long id) {
        siteRepository.deleteById(id);
    }
}
