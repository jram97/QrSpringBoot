package com.qr.code.generator.web.app.service;

import java.util.List;

import com.qr.code.generator.web.app.domain.Campaign;

public interface ICampaignService {
	
	public List<Campaign> findAll();
	
	public void save(Campaign campaign);
	
	public Campaign findOne(Long id);
	
	public void delete(Long id);

	List<Campaign> findByOrderByStartDateDesc();

	List<Campaign> findByOrderByTitleAsc();

	List<Campaign> findByOrderByScansDesc();

}
