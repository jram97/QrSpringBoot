package com.qr.code.generator.web.app.service;

import java.util.List;

import com.qr.code.generator.web.app.domain.BranchOffice;

public interface IBranchOfficeService {
	
	public List<BranchOffice> findAll();
	
	public void save(BranchOffice branchOffice);
	
	public BranchOffice findOne(Long id);
	
	public void delete(Long id);

}
