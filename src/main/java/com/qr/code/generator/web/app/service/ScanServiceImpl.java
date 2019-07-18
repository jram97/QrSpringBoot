package com.qr.code.generator.web.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qr.code.generator.web.app.domain.Scan;
import com.qr.code.generator.web.app.repository.ScanRepository;

@Service
public class ScanServiceImpl implements IScanService {
	
	@Autowired 
	private ScanRepository scanDao;
	
	@Transactional(readOnly = true)
	public List<Scan> findAll() {
		
		return (List<Scan>) scanDao.findAll();
		
	}

	@Transactional
	public void save(Scan scan) {
		
		scanDao.save(scan);
		
	}
	
	@Transactional(readOnly = true)
	public Scan findOne(Long id) {
		
		return scanDao.findById(id).orElse(null);
		
	}
	
	@Transactional
	public void delete(Long id) {
		
		scanDao.deleteById(id);
		
	}


}
