package com.qr.code.generator.web.app.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "scans")
public class Scan implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="scan_date")
	private Date scanDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_campaign")
	private Campaign campaign;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_operating_system")
	private OperatingSystem operatingSystem;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_city")
	private City city;
	
	/*
	 * Getters and Setters
	 */

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Date getScanDate() {
		return scanDate;
	}



	public void setScanDate(Date scanDate) {
		this.scanDate = scanDate;
	}



	


	public Campaign getCampaign() {
		return campaign;
	}



	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}



	public OperatingSystem getOperatingSystem() {
		return operatingSystem;
	}



	public void setOperatingSystem(OperatingSystem operatingSystem) {
		this.operatingSystem = operatingSystem;
	}



	public City getCity() {
		return city;
	}



	public void setCity(City city) {
		this.city = city;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
