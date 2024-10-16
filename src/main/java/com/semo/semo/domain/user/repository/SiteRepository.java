package com.semo.semo.domain.user.repository;

import com.semo.semo.domain.user.model.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteRepository extends JpaRepository<Site, Long> {
}
