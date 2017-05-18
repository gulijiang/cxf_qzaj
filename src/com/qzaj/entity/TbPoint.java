package com.qzaj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TbPoint entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TB_POINT", schema = "QZAJ")
public class TbPoint implements java.io.Serializable {

	// Fields

	private Integer pointId;
	private Integer compId;
	private String pointName;
	private Double pointLon;
	private Double pointLat;
	private Integer pointLevel;
	private String pointDesc;
	private Integer tagPoint;

	// Constructors

	/** default constructor */
	public TbPoint() {
	}

	/** minimal constructor */
	public TbPoint(Integer pointId) {
		this.pointId = pointId;
	}

	/** full constructor */
	public TbPoint(Integer pointId, Integer compId, String pointName,
			Double pointLon, Double pointLat, Integer pointLevel, String pointDesc) {
		this.pointId = pointId;
		this.compId = compId;
		this.pointName = pointName;
		this.pointLon = pointLon;
		this.pointLat = pointLat;
		this.pointLevel = pointLevel;
		this.pointDesc = pointDesc;
	}

	// Property accessors
	@Id
	@Column(name = "POINT_ID", unique = true, nullable = false, precision = 11, scale = 0)
	public Integer getPointId() {
		return this.pointId;
	}

	public void setPointId(Integer pointId) {
		this.pointId = pointId;
	}

	@Column(name = "COMP_ID", precision = 11, scale = 0)
	public Integer getCompId() {
		return this.compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	@Column(name = "POINT_NAME", length = 128)
	public String getPointName() {
		return this.pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

	@Column(name = "POINT_LON", precision = 8, scale = 4)
	public Double getPointLon() {
		return this.pointLon;
	}

	public void setPointLon(Double pointLon) {
		this.pointLon = pointLon;
	}

	@Column(name = "POINT_LAT", precision = 8, scale = 4)
	public Double getPointLat() {
		return this.pointLat;
	}

	public void setPointLat(Double pointLat) {
		this.pointLat = pointLat;
	}

	@Column(name = "POINT_LEVEL", precision = 11, scale = 0)
	public Integer getPointLevel() {
		return this.pointLevel;
	}

	public void setPointLevel(Integer pointLevel) {
		this.pointLevel = pointLevel;
	}

	@Column(name = "POINT_DESC", length = 256)
	public String getPointDesc() {
		return this.pointDesc;
	}

	public void setPointDesc(String pointDesc) {
		this.pointDesc = pointDesc;
	}
	
	@Column(name = "TAG_POINT", precision = 11, scale = 0)
	public Integer getTagPoint() {
		return tagPoint;
	}

	public void setTagPoint(Integer tagPoint) {
		this.tagPoint = tagPoint;
	}
	
}