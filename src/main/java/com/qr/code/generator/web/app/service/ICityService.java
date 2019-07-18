package com.qr.code.generator.web.app.service;

import java.util.List;

import com.qr.code.generator.web.app.domain.City;

public interface ICityService {
	
	List<City> findAll();
	
	void save(City city);
	
	City findOne(Long id);
	
	void delete(Long id);

	List<City> getCampaignCities(Long id);
}
