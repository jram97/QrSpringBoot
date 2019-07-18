package com.qr.code.generator.web.app.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "operating_systems")
public class OperatingSystem implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="os_name")
	private String OSName;
	
	@OneToMany(mappedBy="operatingSystem", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Scan> scans;
	
	/*
	 * Getters and Setters
	 */

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getOSName() {
		return OSName;
	}



	public void setOSName(String oSName) {
		OSName = oSName;
	}



	public List<Scan> getScans() {
		return scans;
	}

	
	public void addScan(Scan scan) {
		scans.add(scan);
	}


	public void setScans(List<Scan> scans) {
		this.scans = scans;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
