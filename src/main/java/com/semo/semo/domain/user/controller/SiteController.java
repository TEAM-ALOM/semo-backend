package com.semo.semo.domain.user.controller;

import com.semo.semo.domain.user.model.entity.Site;
import com.semo.semo.domain.user.model.response.SiteRes;
import com.semo.semo.domain.user.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    /*@GetMapping
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
    }*/

    // 모든 사이트 정보 반환 (200 OK)
    @GetMapping
    public ResponseEntity<List<SiteRes>> getAllSites() {
        List<Site> sites = siteService.getAllSites();
        List<SiteRes> siteResponses = sites.stream()
                .map(SiteRes::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(siteResponses); // 200 OK 상태 코드와 데이터 반환
    }

    // 특정 사이트 정보 반환 (200 OK)
    @GetMapping("/{id}")
    public ResponseEntity<SiteRes> getSiteById(@PathVariable Long id) {
        Site site = siteService.getSiteById(id);
        SiteRes siteResponse = SiteRes.toDto(site);
        return ResponseEntity.ok(siteResponse); // 200 OK 상태 코드와 데이터 반환
    }

    // 새로운 사이트 저장 (201 Created)
    @PostMapping
    public ResponseEntity<SiteRes> saveSite(@RequestBody Site site) {
        Site savedSite = siteService.saveSite(site);
        SiteRes siteResponse = SiteRes.toDto(savedSite);
        return new ResponseEntity<>(siteResponse, HttpStatus.CREATED); // 201 Created 상태 코드와 데이터 반환
    }

    // 사이트 정보 수정 (200 OK)
    @PutMapping("/{id}")
    public ResponseEntity<SiteRes> updateSite(@PathVariable Long id, @RequestBody Site updatedSite) {
        Site site = siteService.updateSite(id, updatedSite);
        SiteRes siteResponse = SiteRes.toDto(site);
        return ResponseEntity.ok(siteResponse); // 200 OK 상태 코드와 데이터 반환
    }

    // 사이트 삭제 (204 No Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSite(@PathVariable Long id) {
        siteService.deleteSite(id);
        return ResponseEntity.noContent().build(); // 204 No Content 상태 코드 반환
    }

}




