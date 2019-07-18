package com.qr.code.generator.web.app.service;

import java.util.List;

import com.qr.code.generator.web.app.domain.OperatingSystem;

public interface IOperatingSystemService {
	
	public List<OperatingSystem> findAll();
	
	public void save(OperatingSystem operatingSystem);
	
	public OperatingSystem findOne(Long id);
	
	public void delete(Long id);

}
