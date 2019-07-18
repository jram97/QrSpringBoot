package com.qr.code.generator.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.qr.code.generator.web.app.domain.Campaign;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    List<Campaign> findByOrderByStartDateDesc();

    List<Campaign> findByOrderByTitleAsc();

    List<Campaign> findByOrderByScansDesc();



}
