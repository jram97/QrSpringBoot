package com.qr.code.generator.web.app.service;

import java.util.List;

import com.qr.code.generator.web.app.domain.Commerce;

public interface ICommerceService {
	
	public List<Commerce> findAll();
	
	public void save(Commerce commerce);
	
	public Commerce findOne(Long id);
	
	public void delete(Long id);

}
