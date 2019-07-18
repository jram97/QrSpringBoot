package com.qr.code.generator.web.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qr.code.generator.web.app.domain.BranchOffice;
import com.qr.code.generator.web.app.repository.BranchOfficeRepository;

@Service
public class BranchOfficeServiceImpl implements IBranchOfficeService {
	
	@Autowired 
	private BranchOfficeRepository branchOfficeDao;
	
	@Transactional(readOnly = true)
	public List<BranchOffice> findAll() {
		
		return (List<BranchOffice>) branchOfficeDao.findAll();
		
	}

	@Transactional
	public void save(BranchOffice branchOffice) {
		
		branchOfficeDao.save(branchOffice);
		
	}
	
	@Transactional(readOnly = true)
	public BranchOffice findOne(Long id) {
		
		return branchOfficeDao.findById(id).orElse(null);
		
	}
	
	@Transactional
	public void delete(Long id) {
		
		branchOfficeDao.deleteById(id);
		
	}

}
