package com.semo.semo.domain.user.controller;

import com.semo.semo.domain.user.model.entity.Site;
import com.semo.semo.domain.user.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sites")
//사용자 응답 처리 컨트롤러 + site
public class SiteController {

    //site service 받아와서 사용한다
    private final SiteService siteService;

    @Autowired
    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }
    //모든 사이트 정보를 받아온다
    @GetMapping
    public List<Site> getAllSites() {
        return siteService.getAllSites();
    }
    //특정 사이트의 정보를 가저오고, 그 url 이 /id 이다
    @GetMapping("/{id}")
    public Site getSiteById(@PathVariable Long id) {
        return siteService.getSiteById(id);  // Optional 처리 없이 바로 Site 객체 반환
    }
    //사이트 저장
    @PostMapping
    public Site saveSite(@RequestBody Site site) {
        return siteService.saveSite(site);
    }
    //사이트 정보 수정
    @PutMapping("/{id}")
    public Site updateSite(@PathVariable Long id, @RequestBody Site site) {
        return siteService.updateSite(id, site);
    }
    //사이트 정보 삭제
    @DeleteMapping("/{id}")
    public void deleteSite(@PathVariable Long id) {
        siteService.deleteSite(id);
    }
}
