package com.qzaj.mysql.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * VcTag entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "vc_tag", catalog = "qzaj")
public class VcTag implements java.io.Serializable {

	// Fields

	private Integer tagId;
	private Integer tagPoint;
	private String tagName;
	private String tagDesc;
	private String tagUnit;
	private Integer tagDecimal;
	private Short tagRunning;
	private Double tagLlimit;
	private Double tagHlimit;
	private Double tagLwarn;
	private Double tagHwarn;
	private Double tagLalarm;
	private Double tagHalarm;
	private Double tagValue;
	private Integer tagAlarm;
	private Integer tagStamp;
	private String tagUser;
	private String tagMobile;



	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "tag_id", unique = true, nullable = false)
	public Integer getTagId() {
		return this.tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	@Column(name = "tag_point", nullable = false)
	public Integer getTagPoint() {
		return this.tagPoint;
	}

	public void setTagPoint(Integer tagPoint) {
		this.tagPoint = tagPoint;
	}

	@Column(name = "tag_name", nullable = false, length = 64)
	public String getTagName() {
		return this.tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@Column(name = "tag_desc", length = 128)
	public String getTagDesc() {
		return this.tagDesc;
	}

	public void setTagDesc(String tagDesc) {
		this.tagDesc = tagDesc;
	}

	@Column(name = "tag_unit", length = 32)
	public String getTagUnit() {
		return this.tagUnit;
	}

	public void setTagUnit(String tagUnit) {
		this.tagUnit = tagUnit;
	}

	@Column(name = "tag_decimal")
	public Integer getTagDecimal() {
		return this.tagDecimal;
	}

	public void setTagDecimal(Integer tagDecimal) {
		this.tagDecimal = tagDecimal;
	}

	@Column(name = "tag_running")
	public Short getTagRunning() {
		return this.tagRunning;
	}

	public void setTagRunning(Short tagRunning) {
		this.tagRunning = tagRunning;
	}

	@Column(name = "tag_llimit", precision = 22, scale = 0)
	public Double getTagLlimit() {
		return this.tagLlimit;
	}

	public void setTagLlimit(Double tagLlimit) {
		this.tagLlimit = tagLlimit;
	}

	@Column(name = "tag_hlimit", precision = 22, scale = 0)
	public Double getTagHlimit() {
		return this.tagHlimit;
	}

	public void setTagHlimit(Double tagHlimit) {
		this.tagHlimit = tagHlimit;
	}

	@Column(name = "tag_lwarn", precision = 22, scale = 0)
	public Double getTagLwarn() {
		return this.tagLwarn;
	}

	public void setTagLwarn(Double tagLwarn) {
		this.tagLwarn = tagLwarn;
	}

	@Column(name = "tag_hwarn", precision = 22, scale = 0)
	public Double getTagHwarn() {
		return this.tagHwarn;
	}

	public void setTagHwarn(Double tagHwarn) {
		this.tagHwarn = tagHwarn;
	}

	@Column(name = "tag_lalarm", precision = 22, scale = 0)
	public Double getTagLalarm() {
		return this.tagLalarm;
	}

	public void setTagLalarm(Double tagLalarm) {
		this.tagLalarm = tagLalarm;
	}

	@Column(name = "tag_halarm", precision = 22, scale = 0)
	public Double getTagHalarm() {
		return this.tagHalarm;
	}

	public void setTagHalarm(Double tagHalarm) {
		this.tagHalarm = tagHalarm;
	}

	@Column(name = "tag_value", nullable = false, precision = 22, scale = 0)
	public Double getTagValue() {
		return this.tagValue;
	}

	public void setTagValue(Double tagValue) {
		this.tagValue = tagValue;
	}

	@Column(name = "tag_alarm")
	public Integer getTagAlarm() {
		return this.tagAlarm;
	}

	public void setTagAlarm(Integer tagAlarm) {
		this.tagAlarm = tagAlarm;
	}

	@Column(name = "tag_stamp")
	public Integer getTagStamp() {
		return this.tagStamp;
	}

	public void setTagStamp(Integer tagStamp) {
		this.tagStamp = tagStamp;
	}

	@Column(name = "tag_user", length = 65535)
	public String getTagUser() {
		return this.tagUser;
	}

	public void setTagUser(String tagUser) {
		this.tagUser = tagUser;
	}

	@Column(name = "tag_mobile", length = 65535)
	public String getTagMobile() {
		return this.tagMobile;
	}

	public void setTagMobile(String tagMobile) {
		this.tagMobile = tagMobile;
	}

}