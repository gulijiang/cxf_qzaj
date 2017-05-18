package com.qzaj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TbArea entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TB_AREA", schema = "QZAJ")
public class TbArea implements java.io.Serializable {

	// Fields

	private Integer areaId;
	private String areaName;

	// Constructors

	/** default constructor */
	public TbArea() {
	}

	/** minimal constructor */
	public TbArea(Integer areaId) {
		this.areaId = areaId;
	}

	/** full constructor */
	public TbArea(Integer areaId, String areaName) {
		this.areaId = areaId;
		this.areaName = areaName;
	}

	// Property accessors
	@Id
	@Column(name = "AREA_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	@Column(name = "AREA_NAME", length = 128)
	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

}