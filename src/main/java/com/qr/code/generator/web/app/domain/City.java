package com.qr.code.generator.web.app.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
public class City implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String city;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_country")
	private Country country;
	
	@OneToMany(mappedBy="city", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
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



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public Country getCountry() {
		return country;
	}



	public void setCountry(Country country) {
		this.country = country;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	public void addScan(Scan scan) {
		scans.add(scan);
	}


	public List<Scan> getScans() {
		return scans;
	}



	public void setScans(List<Scan> scans) {
		this.scans = scans;
	}




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
