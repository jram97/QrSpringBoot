package com.qr.code.generator.web.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qr.code.generator.web.app.domain.Campaign;
import com.qr.code.generator.web.app.repository.CampaignRepository;

@Service
public class CampaignServiceImpl implements ICampaignService {
	
	@Autowired 
	private CampaignRepository campaignDao;
	
	@Transactional(readOnly = true)
	public List<Campaign> findAll() {
		
		return (List<Campaign>) campaignDao.findAll();
		
	}

	@Transactional
	public void save(Campaign campaign) {
		
		campaignDao.save(campaign);
		
	}
	
	@Transactional(readOnly = true)
	public Campaign findOne(Long id) {
		
		return campaignDao.findById(id).orElse(null);
		
	}
	
	@Transactional
	public void delete(Long id) {
		
		campaignDao.deleteById(id);
		
	}

	@Override
	public List<Campaign> findByOrderByStartDateDesc() {
		return campaignDao.findByOrderByStartDateDesc();
	}

	@Override
	public List<Campaign> findByOrderByTitleAsc() {
		return campaignDao.findByOrderByTitleAsc();
	}


	@Override
	public List<Campaign> findByOrderByScansDesc() {
		return campaignDao.findByOrderByScansDesc();
	}

	@Override
	public List<Campaign> findIsAvailable() {
		return campaignDao.findByIsAvailable();
	}

}
