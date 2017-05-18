package com.qzaj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TbIndustry entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TB_INDUSTRY", schema = "QZAJ")
public class TbIndustry implements java.io.Serializable {

	// Fields

	private Integer industryId;
	private String industryName;

	// Constructors

	/** default constructor */
	public TbIndustry() {
	}

	/** minimal constructor */
	public TbIndustry(Integer industryId) {
		this.industryId = industryId;
	}

	/** full constructor */
	public TbIndustry(Integer industryId, String industryName) {
		this.industryId = industryId;
		this.industryName = industryName;
	}

	// Property accessors
	@Id
	@Column(name = "INDUSTRY_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getIndustryId() {
		return this.industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	@Column(name = "INDUSTRY_NAME", length = 64)
	public String getIndustryName() {
		return this.industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

}