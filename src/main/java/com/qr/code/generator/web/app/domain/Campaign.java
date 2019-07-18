package com.qr.code.generator.web.app.domain;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "campaigns")
public class Campaign implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String image;
	
	private String title;
	
	private String description;
	
	@Column(name="website")
	private String webSite;
	
	@Column(name="img_qr", columnDefinition = "TEXT")
	private String imgQR;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="start_date")
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="end_date")
	private Date endDate;

	@Column(name = "is_available")
	private Boolean isAvailable;

	public Boolean getAvailable() {
		return isAvailable;
	}

	public void setAvailable(Boolean available) {
		isAvailable = available;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_branch_office")
	private BranchOffice branchOffice;
	
	@OneToMany(mappedBy="campaign", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Scan> scans;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserAdmin user;

	public UserAdmin getUser() {
		return user;
	}

	public void setUser(UserAdmin user) {
		this.user = user;
	}
	/*
	 * Getters and Setters
	 */
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getWebSite() {
		return webSite;
	}



	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}



	public String getImgQR() {
		return imgQR;
	}



	public void setImgQR(String imgQR) {
		this.imgQR = imgQR;
	}



	public Date getStartDate() {
		return startDate;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public Date getEndDate() {
		return endDate;
	}



	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


	public BranchOffice getBranchOffice() {
		return branchOffice;
	}



	public void setBranchOffice(BranchOffice branchOffice) {
		this.branchOffice = branchOffice;
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


	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Campaign [id=" + id + ", image=" + image + ", title=" + title + ", description=" + description
				+ ", webSite=" + webSite + ", imgQR=" + imgQR + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", isAvailable=" + isAvailable + ", branchOffice=" + branchOffice + ", scans=" + scans + "]";
	}
	
	

}
