package com.qr.code.generator.web.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qr.code.generator.web.app.domain.OperatingSystem;
import com.qr.code.generator.web.app.repository.OperatingSystemRepository;

@Service
public class OperatingSystemServiceImpl implements IOperatingSystemService {
	
	@Autowired 
	private OperatingSystemRepository operatingSystemDao;
	
	@Transactional(readOnly = true)
	public List<OperatingSystem> findAll() {
		
		return (List<OperatingSystem>) operatingSystemDao.findAll();
		
	}

	@Transactional
	public void save(OperatingSystem operatingSystem) {
		
		operatingSystemDao.save(operatingSystem);
		
	}
	
	@Transactional(readOnly = true)
	public OperatingSystem findOne(Long id) {
		
		return operatingSystemDao.findById(id).orElse(null);
		
	}
	
	@Transactional
	public void delete(Long id) {
		
		operatingSystemDao.deleteById(id);
		
	}

}
