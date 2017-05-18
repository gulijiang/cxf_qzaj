package com.qzaj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TbComp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TB_COMP", schema = "QZAJ")
public class TbComp implements java.io.Serializable {

	// Fields

	private Integer compId;
	private String compNo;
	private String compName;
	private String compCorp;
	private String compAddr;
	private Double compLon;
	private Double compLat;
	private Integer compLevel;
	private Integer compIndustry;
	private Integer compArea;
	private String compDesc;
	private String compBosch;
	private String compFile;

	// Constructors

	/** default constructor */
	public TbComp() {
	}

	/** minimal constructor */
	public TbComp(Integer compId) {
		this.compId = compId;
	}

	/** full constructor */
	public TbComp(Integer compId, String compNo, String compName,
			String compCorp, String compAddr, Double compLon, Double compLat,
			Integer compLevel, Integer compIndustry, Integer compArea,
			String compDesc, String compBosch, String compFile) {
		this.compId = compId;
		this.compNo = compNo;
		this.compName = compName;
		this.compCorp = compCorp;
		this.compAddr = compAddr;
		this.compLon = compLon;
		this.compLat = compLat;
		this.compLevel = compLevel;
		this.compIndustry = compIndustry;
		this.compArea = compArea;
		this.compDesc = compDesc;
		this.compBosch = compBosch;
		this.compFile = compFile;
	}

	// Property accessors
	@Id
	@Column(name = "COMP_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getCompId() {
		return this.compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	@Column(name = "COMP_NO", length = 16)
	public String getCompNo() {
		return this.compNo;
	}

	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}

	@Column(name = "COMP_NAME", length = 128)
	public String getCompName() {
		return this.compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	@Column(name = "COMP_CORP", length = 64)
	public String getCompCorp() {
		return this.compCorp;
	}

	public void setCompCorp(String compCorp) {
		this.compCorp = compCorp;
	}

	@Column(name = "COMP_ADDR", length = 256)
	public String getCompAddr() {
		return this.compAddr;
	}

	public void setCompAddr(String compAddr) {
		this.compAddr = compAddr;
	}

	@Column(name = "COMP_LON", precision = 8, scale = 4)
	public Double getCompLon() {
		return this.compLon;
	}

	public void setCompLon(Double compLon) {
		this.compLon = compLon;
	}

	@Column(name = "COMP_LAT", precision = 8, scale = 4)
	public Double getCompLat() {
		return this.compLat;
	}

	public void setCompLat(Double compLat) {
		this.compLat = compLat;
	}

	@Column(name = "COMP_LEVEL", precision = 22, scale = 0)
	public Integer getCompLevel() {
		return this.compLevel;
	}

	public void setCompLevel(Integer compLevel) {
		this.compLevel = compLevel;
	}

	@Column(name = "COMP_INDUSTRY", precision = 22, scale = 0)
	public Integer getCompIndustry() {
		return this.compIndustry;
	}

	public void setCompIndustry(Integer compIndustry) {
		this.compIndustry = compIndustry;
	}

	@Column(name = "COMP_AREA", precision = 22, scale = 0)
	public Integer getCompArea() {
		return this.compArea;
	}

	public void setCompArea(Integer compArea) {
		this.compArea = compArea;
	}

	@Column(name = "COMP_DESC", length = 2000)
	public String getCompDesc() {
		return this.compDesc;
	}

	public void setCompDesc(String compDesc) {
		this.compDesc = compDesc;
	}

	@Column(name = "COMP_BOSCH", length = 128)
	public String getCompBosch() {
		return this.compBosch;
	}

	public void setCompBosch(String compBosch) {
		this.compBosch = compBosch;
	}

	@Column(name = "COMP_FILE", length = 32)
	public String getCompFile() {
		return this.compFile;
	}

	public void setCompFile(String compFile) {
		this.compFile = compFile;
	}

}