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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "branch_offices")
public class BranchOffice implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="branch_office")
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_commerce")
	private Commerce commerce;
	
	@OneToMany(mappedBy="branchOffice", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Campaign> campaings;
	/*
	 * Espacio para getters and Setters
	 */
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


	public Commerce getCommerce() {
		return commerce;
	}



	public void setCommerce(Commerce commerce) {
		this.commerce = commerce;
	}



	public List<Campaign> getCampaings() {
		return campaings;
	}



	public void setCampaings(List<Campaign> campaings) {
		this.campaings = campaings;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
