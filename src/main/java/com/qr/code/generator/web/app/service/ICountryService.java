package com.qr.code.generator.web.app.service;

import java.util.List;

import com.qr.code.generator.web.app.domain.Country;

public interface ICountryService {
	
	List<Country> findAll();
	
	void save(Country country);
	
	Country findOne(Long id);
	
	void delete(Long id);

	List<Country> getCampaignCountry(Long id);

}
