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
 * TbAlarm entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TB_ALARM", schema = "QZAJ")
public class TbAlarm implements java.io.Serializable {

	// Fields

	private Integer alarmId;
	private String alarmTagname;
	private Integer alarmType;
	private Double alarmValue;
	private Date alarmStamp;
	private String alarmConfirm;
	private Date alarmClear;
	private Integer alarmMode;
	private Integer compId;
	private String tagSign;
	private String tagTank;
	private Integer alamOrigin;
	private Integer sendState;

	// Constructors

	/** default constructor */
	public TbAlarm() {
	}

	/** minimal constructor */
	public TbAlarm(Integer alarmId) {
		this.alarmId = alarmId;
	}

	// Property accessors
	@Id
	@Column(name = "ALARM_ID", unique = true, nullable = false, precision = 11, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_ALARM")
	@SequenceGenerator (name= "SEQ_ALARM" , sequenceName= "SEQ_ALARM" ,allocationSize = 1, initialValue = 1) 
	public Integer getAlarmId() {
		return this.alarmId;
	}

	public void setAlarmId(Integer alarmId) {
		this.alarmId = alarmId;
	}

	@Column(name = "ALARM_TAGNAME", length = 64)
	public String getAlarmTagname() {
		return this.alarmTagname;
	}

	public void setAlarmTagname(String alarmTagname) {
		this.alarmTagname = alarmTagname;
	}

	@Column(name = "ALARM_TYPE", precision = 11, scale = 0)
	public Integer getAlarmType() {
		return this.alarmType;
	}

	public void setAlarmType(Integer alarmType) {
		this.alarmType = alarmType;
	}

	@Column(name = "ALARM_VALUE", precision = 4)
	public Double getAlarmValue() {
		return this.alarmValue;
	}

	public void setAlarmValue(Double alarmValue) {
		this.alarmValue = alarmValue;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ALARM_STAMP", length = 7)
	public Date getAlarmStamp() {
		return this.alarmStamp;
	}

	public void setAlarmStamp(Date alarmStamp) {
		this.alarmStamp = alarmStamp;
	}

	@Column(name = "ALARM_CONFIRM", length = 64)
	public String getAlarmConfirm() {
		return this.alarmConfirm;
	}

	public void setAlarmConfirm(String alarmConfirm) {
		this.alarmConfirm = alarmConfirm;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ALARM_CLEAR", length = 7)
	public Date getAlarmClear() {
		return this.alarmClear;
	}

	public void setAlarmClear(Date alarmClear) {
		this.alarmClear = alarmClear;
	}

	@Column(name = "ALARM_MODE", precision = 11, scale = 0)
	public Integer getAlarmMode() {
		return this.alarmMode;
	}

	public void setAlarmMode(Integer alarmMode) {
		this.alarmMode = alarmMode;
	}
	
	@Column(name = "COMP_ID")
	public Integer getCompId() {
		return compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}
	
	@Column(name = "TAG_SIGN", length = 64)
	public String getTagSign() {
		return tagSign;
	}

	public void setTagSign(String tagSign) {
		this.tagSign = tagSign;
	}
	
	@Column(name = "TAG_TANK", length = 32)
	public String getTagTank() {
		return tagTank;
	}

	public void setTagTank(String tagTank) {
		this.tagTank = tagTank;
	}
	
	@Column(name = "ALAM_ORIGIN", precision = 11, scale = 0)
	public Integer getAlamOrigin() {
		return alamOrigin;
	}

	public void setAlamOrigin(Integer alamOrigin) {
		this.alamOrigin = alamOrigin;
	}
	
	@Column(name = "SEND_STATE", precision = 11, scale = 0)
	public Integer getSendState() {
		return sendState;
	}

	public void setSendState(Integer sendState) {
		this.sendState = sendState;
	}
	
	
	
}