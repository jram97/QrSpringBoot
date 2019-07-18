package com.qr.code.generator.web.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qr.code.generator.web.app.domain.City;
import com.qr.code.generator.web.app.repository.CityRepository;

@Service
public class CityServiceImpl implements ICityService {
	
	@Autowired 
	private CityRepository cityDao;
	
	@Transactional(readOnly = true)
	public List<City> findAll() {
		
		return (List<City>) cityDao.findAll();
		
	}

	@Transactional
	public void save(City city) {
		
		cityDao.save(city);
		
	}
	
	@Transactional(readOnly = true)
	public City findOne(Long id) {
		
		return cityDao.findById(id).orElse(null);
		
	}
	
	@Transactional
	public void delete(Long id) {
		
		cityDao.deleteById(id);
		
	}

	@Override
	public List<City> getCampaignCities(Long id) {
		return cityDao.getCampaignCities(id);
	}

}
