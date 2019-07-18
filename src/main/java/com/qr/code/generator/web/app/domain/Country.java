package com.qr.code.generator.web.app.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class Country implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String country;
	
	@OneToMany(mappedBy="country", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	private List<City> cities;

	/*
	 * Getters and Setters
	 */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public void addCity(City city) {
		cities.add(city);
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
