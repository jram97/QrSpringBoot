package com.qr.code.generator.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qr.code.generator.web.app.domain.Campaign;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    List<Campaign> findByOrderByStartDateDesc();

    List<Campaign> findByOrderByTitleAsc();

    List<Campaign> findByOrderByScansDesc();

    @Query(
    		value = "SELECT * FROM campaigns WHERE is_available = 1",
    		nativeQuery = true)
    List<Campaign> findByIsAvailable();

}
