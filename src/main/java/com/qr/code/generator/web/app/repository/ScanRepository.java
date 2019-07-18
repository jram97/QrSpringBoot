package com.qr.code.generator.web.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.qr.code.generator.web.app.domain.Scan;

public interface ScanRepository extends CrudRepository<Scan, Long> {


}
