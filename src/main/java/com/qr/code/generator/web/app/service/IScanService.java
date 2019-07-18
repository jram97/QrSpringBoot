package com.qr.code.generator.web.app.service;

import java.util.List;

import com.qr.code.generator.web.app.domain.Scan;

public interface IScanService {
	
	public List<Scan> findAll();
	
	public void save(Scan scan);
	
	public Scan findOne(Long id);
	
	public void delete(Long id);


}
