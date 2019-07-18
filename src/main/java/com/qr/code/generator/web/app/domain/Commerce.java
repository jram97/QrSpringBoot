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
@Table(name = "shops")
public class Commerce implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="commerce")
	private String name;
	
	@OneToMany(mappedBy="commerce", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BranchOffice> branchOffices;
	

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
	

	public List<BranchOffice> getBranchOffices() {
		return branchOffices;
	}



	public void setBranchOffices(List<BranchOffice> branchOffices) {
		this.branchOffices = branchOffices;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
