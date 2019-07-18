package com.qr.code.generator.web.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qr.code.generator.web.app.domain.Commerce;
import com.qr.code.generator.web.app.repository.CommerceRepository;

@Service
public class CommerceServiceImpl implements ICommerceService {
	
	@Autowired 
	private CommerceRepository commerceDao;
	
	@Transactional(readOnly = true)
	public List<Commerce> findAll() {
		
		return (List<Commerce>) commerceDao.findAll();
		
	}

	@Transactional
	public void save(Commerce commerce) {
		
		commerceDao.save(commerce);
		
	}
	
	@Transactional(readOnly = true)
	public Commerce findOne(Long id) {
		
		return commerceDao.findById(id).orElse(null);
		
	}
	
	@Transactional
	public void delete(Long id) {
		
		commerceDao.deleteById(id);
		
	}

}
