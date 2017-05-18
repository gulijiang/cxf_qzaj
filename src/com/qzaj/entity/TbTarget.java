package com.qzaj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TbTarget entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TB_TARGET", schema = "QZAJ")
public class TbTarget implements java.io.Serializable {

	// Fields

	private Integer targetId;
	private String targetName;
	private String targetMatter;
	private Integer pointId;
	private Integer compId;
	private String targetDesc;

	// Constructors

	/** default constructor */
	public TbTarget() {
	}

	/** minimal constructor */
	public TbTarget(Integer targetId) {
		this.targetId = targetId;
	}

	/** full constructor */
	public TbTarget(Integer targetId, String targetName, String targetMatter,
			Integer pointId, Integer compId, String targetDesc) {
		this.targetId = targetId;
		this.targetName = targetName;
		this.targetMatter = targetMatter;
		this.pointId = pointId;
		this.compId = compId;
		this.targetDesc = targetDesc;
	}

	// Property accessors
	@Id
	@Column(name = "TARGET_ID", unique = true, nullable = false, precision = 11, scale = 0)
	public Integer getTargetId() {
		return this.targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	@Column(name = "TARGET_NAME", length = 32)
	public String getTargetName() {
		return this.targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	@Column(name = "TARGET_MATTER", length = 32)
	public String getTargetMatter() {
		return this.targetMatter;
	}

	public void setTargetMatter(String targetMatter) {
		this.targetMatter = targetMatter;
	}

	@Column(name = "POINT_ID", precision = 11, scale = 0)
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

	@Column(name = "TARGET_DESC", length = 256)
	public String getTargetDesc() {
		return this.targetDesc;
	}

	public void setTargetDesc(String targetDesc) {
		this.targetDesc = targetDesc;
	}

}