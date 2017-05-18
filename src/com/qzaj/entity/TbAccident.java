package com.qzaj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TbAccident entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TB_ACCIDENT", schema = "QZAJ")
public class TbAccident implements java.io.Serializable {

	// Fields

	private Integer accidentId;
	private String accidentName;
	private Double accidentLon;
	private Double accidenLat;
	private Integer accidentKill;
	private Integer accidentSer;
	private String accidentDes;
	private Integer compId;

	// Constructors

	/** default constructor */
	public TbAccident() {
	}

	// Property accessors
	@Id
	@Column(name = "ACCIDENT_ID", unique = true, nullable = false, precision = 11, scale = 0)
	public Integer getAccidentId() {
		return this.accidentId;
	}

	public void setAccidentId(Integer accidentId) {
		this.accidentId = accidentId;
	}

	@Column(name = "ACCIDENT_NAME", length = 32)
	public String getAccidentName() {
		return this.accidentName;
	}

	public void setAccidentName(String accidentName) {
		this.accidentName = accidentName;
	}

	@Column(name = "ACCIDENT_LON", precision = 8, scale = 4)
	public Double getAccidentLon() {
		return this.accidentLon;
	}

	public void setAccidentLon(Double accidentLon) {
		this.accidentLon = accidentLon;
	}

	@Column(name = "ACCIDEN_LAT", precision = 8, scale = 4)
	public Double getAccidenLat() {
		return this.accidenLat;
	}

	public void setAccidenLat(Double accidenLat) {
		this.accidenLat = accidenLat;
	}

	@Column(name = "ACCIDENT_KILL", precision = 22, scale = 0)
	public Integer getAccidentKill() {
		return this.accidentKill;
	}

	public void setAccidentKill(Integer accidentKill) {
		this.accidentKill = accidentKill;
	}

	@Column(name = "ACCIDENT_SER", precision = 22, scale = 0)
	public Integer getAccidentSer() {
		return this.accidentSer;
	}

	public void setAccidentSer(Integer accidentSer) {
		this.accidentSer = accidentSer;
	}

	@Column(name = "ACCIDENT_DES", length = 128)
	public String getAccidentDes() {
		return this.accidentDes;
	}

	public void setAccidentDes(String accidentDes) {
		this.accidentDes = accidentDes;
	}

	@Column(name = "COMP_ID", precision = 11, scale = 0)
	public Integer getCompId() {
		return this.compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}

}