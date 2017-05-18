package com.qzaj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TbSign entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TB_SIGN", schema = "QZAJ")
public class TbSign implements java.io.Serializable {

	// Fields

	private Integer signId;
	private String signName;
	private Integer signState;

	// Constructors

	/** default constructor */
	public TbSign() {
	}


	// Property accessors
	@Id
	@Column(name = "SIGN_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getSignId() {
		return this.signId;
	}

	public void setSignId(Integer signId) {
		this.signId = signId;
	}

	@Column(name = "SIGN_NAME", length = 510)
	public String getSignName() {
		return this.signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	@Column(name = "SIGN_STATE")
	public Integer getSignState() {
		return signState;
	}


	public void setSignState(Integer signState) {
		this.signState = signState;
	}
	
	
}