package com.qr.code.generator.web.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qr.code.generator.web.app.domain.Country;
import com.qr.code.generator.web.app.repository.CountryRepository;

@Service
public class CountryServiceImpl implements ICountryService {
	
	@Autowired 
	private CountryRepository countryDao;
	
	@Transactional(readOnly = true)
	public List<Country> findAll() {
		
		return (List<Country>) countryDao.findAll();
		
	}

	@Transactional
	public void save(Country country) {
		
		countryDao.save(country);
		
	}
	
	@Transactional(readOnly = true)
	public Country findOne(Long id) {
		
		return countryDao.findById(id).orElse(null);
		
	}
	
	@Transactional
	public void delete(Long id) {
		
		countryDao.deleteById(id);
		
	}

	@Override
	public List<Country> getCampaignCountry(Long id) {
		return countryDao.getCampaignCountry(id);
	}

}
