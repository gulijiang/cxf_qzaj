package com.qzaj.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TbTag entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TB_TAG", schema = "QZAJ")
public class TbTag implements java.io.Serializable {

	// Fields

	private Integer tagId;
	private Integer targetId;
	private Integer compId;
	private Integer pointId;
	private String tagName;
	private String tagTank;
	private String tagSign;
	private String tagUnit;
	private Integer tagDecimal;
	private Double tagLlimit;
	private Double tagHlimit;
	private Double tagLwarn;
	private Double tagHwarn;
	private Double tagLalarm;
	private Double tagHalarm;
	private Double tagValue;
	private Integer tagAlarm;
	private String tagUser;
	private String tagMobile;
	private Date tagTime;

	// Constructors


	@Id
	@Column(name = "TAG_ID", unique = true, nullable = false, precision = 11, scale = 0)
	public Integer getTagId() {
		return this.tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	@Column(name = "TARGET_ID", precision = 11, scale = 0)
	public Integer getTargetId() {
		return this.targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	@Column(name = "COMP_ID", precision = 11, scale = 0)
	public Integer getCompId() {
		return this.compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	@Column(name = "POINT_ID", precision = 11, scale = 0)
	public Integer getPointId() {
		return this.pointId;
	}

	public void setPointId(Integer pointId) {
		this.pointId = pointId;
	}

	@Column(name = "TAG_NAME", length = 64)
	public String getTagName() {
		return this.tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@Column(name = "TAG_TANK", length = 32)
	public String getTagTank() {
		return this.tagTank;
	}

	public void setTagTank(String tagTank) {
		this.tagTank = tagTank;
	}

	@Column(name = "TAG_SIGN", length = 128)
	public String getTagSign() {
		return this.tagSign;
	}

	public void setTagSign(String tagSign) {
		this.tagSign = tagSign;
	}

	@Column(name = "TAG_UNIT", length = 32)
	public String getTagUnit() {
		return this.tagUnit;
	}

	public void setTagUnit(String tagUnit) {
		this.tagUnit = tagUnit;
	}

	@Column(name = "TAG_DECIMAL", precision = 11, scale = 0)
	public Integer getTagDecimal() {
		return this.tagDecimal;
	}

	public void setTagDecimal(Integer tagDecimal) {
		this.tagDecimal = tagDecimal;
	}

	@Column(name = "TAG_LLIMIT")
	public Double getTagLlimit() {
		return this.tagLlimit;
	}

	public void setTagLlimit(Double tagLlimit) {
		this.tagLlimit = tagLlimit;
	}

	@Column(name = "TAG_HLIMIT")
	public Double getTagHlimit() {
		return this.tagHlimit;
	}

	public void setTagHlimit(Double tagHlimit) {
		this.tagHlimit = tagHlimit;
	}

	@Column(name = "TAG_LWARN")
	public Double getTagLwarn() {
		return this.tagLwarn;
	}

	public void setTagLwarn(Double tagLwarn) {
		this.tagLwarn = tagLwarn;
	}

	@Column(name = "TAG_HWARN")
	public Double getTagHwarn() {
		return this.tagHwarn;
	}

	public void setTagHwarn(Double tagHwarn) {
		this.tagHwarn = tagHwarn;
	}

	@Column(name = "TAG_LALARM")
	public Double getTagLalarm() {
		return this.tagLalarm;
	}

	public void setTagLalarm(Double tagLalarm) {
		this.tagLalarm = tagLalarm;
	}

	@Column(name = "TAG_HALARM")
	public Double getTagHalarm() {
		return this.tagHalarm;
	}

	public void setTagHalarm(Double tagHalarm) {
		this.tagHalarm = tagHalarm;
	}

	@Column(name = "TAG_VALUE")
	public Double getTagValue() {
		return this.tagValue;
	}

	public void setTagValue(Double tagValue) {
		this.tagValue = tagValue;
	}

	@Column(name = "TAG_ALARM", precision = 11, scale = 0)
	public Integer getTagAlarm() {
		return this.tagAlarm;
	}

	public void setTagAlarm(Integer tagAlarm) {
		this.tagAlarm = tagAlarm;
	}

	@Column(name = "TAG_USER", length = 128)
	public String getTagUser() {
		return this.tagUser;
	}

	public void setTagUser(String tagUser) {
		this.tagUser = tagUser;
	}

	@Column(name = "TAG_MOBILE", length = 128)
	public String getTagMobile() {
		return this.tagMobile;
	}

	public void setTagMobile(String tagMobile) {
		this.tagMobile = tagMobile;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TAG_TIME", length = 7)
	public Date getTagTime() {
		return this.tagTime;
	}

	public void setTagTime(Date tagTime) {
		this.tagTime = tagTime;
	}

}