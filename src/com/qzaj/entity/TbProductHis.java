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
 * TbProduct entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TB_PRODUCT_HIS", schema = "QZAJ")
public class TbProductHis implements java.io.Serializable {

	// Fields

	private Integer productId;
	private String productName;
	private String mainDan;
	private String mainTec;
	private Integer productState;
	private Integer compId;
	private String productDesc;
	private Date productTime;
	private Double value;
	private String signDesc;
	
	// Constructors

	/** default constructor */
	public TbProductHis() {
	}

	/** minimal constructor */
	public TbProductHis(Integer productId) {
		this.productId = productId;
	}


	// Property accessors
	@Id
	@Column(name = "PRODUCT_ID", unique = true, nullable = false, precision = 11, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_PRODUCT_HIS")
	@SequenceGenerator (name= "SEQ_PRODUCT_HIS" , sequenceName= "SEQ_PRODUCT_HIS" ,allocationSize = 1, initialValue = 1) 
	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Column(name = "PRODUCT_NAME", length = 32)
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "MAIN_DAN", length = 128,updatable=false)
	public String getMainDan() {
		return this.mainDan;
	}

	public void setMainDan(String mainDan) {
		this.mainDan = mainDan;
	}

	@Column(name = "MAIN_TEC", length = 128,updatable=false)
	public String getMainTec() {
		return this.mainTec;
	}

	public void setMainTec(String mainTec) {
		this.mainTec = mainTec;
	}

	@Column(name = "PRODUCT_STATE", precision = 11, scale = 0)
	public Integer getProductState() {
		return this.productState;
	}

	public void setProductState(Integer productState) {
		this.productState = productState;
	}

	@Column(name = "COMP_ID", precision = 11, scale = 0)
	public Integer getCompId() {
		return this.compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	@Column(name = "PRODUCT_DESC", length = 256)
	public String getProductDesc() {
		return this.productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PRODUCT_TIME", length = 7)
	public Date getProductTime() {
		return this.productTime;
	}

	public void setProductTime(Date productTime) {
		this.productTime = productTime;
	}
	@Column(name = "VALUE", precision = 11, scale = 0)
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
	@Column(name = "SIGN_DESC", length = 256)
	public String getSignDesc() {
		return signDesc;
	}

	public void setSignDesc(String signDesc) {
		this.signDesc = signDesc;
	}

}