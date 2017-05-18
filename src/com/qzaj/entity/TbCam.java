package com.qzaj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TbCam entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="TB_CAM"
    ,schema="QZAJ"
)

public class TbCam  implements java.io.Serializable {


    // Fields    

     private Integer camId;
     private Integer camPoint;
     private String camName;
     private String camIp;
     private Integer camPort;
     private String camUsername;
     private String camPassword;
     private String camDevid;
     private String camChannel;


    // Constructors

    /** default constructor */
    public TbCam() {
    }


   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="CAM_ID", unique=true, nullable=false, precision=11, scale=0)

    public Integer getCamId() {
        return this.camId;
    }
    
    public void setCamId(Integer camId) {
        this.camId = camId;
    }
    
    @Column(name="CAM_POINT", nullable=false, precision=11, scale=0)

    public Integer getCamPoint() {
        return this.camPoint;
    }
    
    public void setCamPoint(Integer camPoint) {
        this.camPoint = camPoint;
    }
    
    @Column(name="CAM_NAME", length=64)

    public String getCamName() {
        return this.camName;
    }
    
    public void setCamName(String camName) {
        this.camName = camName;
    }
    
    @Column(name="CAM_IP", length=128)

    public String getCamIp() {
        return this.camIp;
    }
    
    public void setCamIp(String camIp) {
        this.camIp = camIp;
    }
    
    @Column(name="CAM_PORT", precision=11, scale=0)

    public Integer getCamPort() {
        return this.camPort;
    }
    
    public void setCamPort(Integer camPort) {
        this.camPort = camPort;
    }
    
    @Column(name="CAM_USERNAME", length=64)

    public String getCamUsername() {
        return this.camUsername;
    }
    
    public void setCamUsername(String camUsername) {
        this.camUsername = camUsername;
    }
    
    @Column(name="CAM_PASSWORD", length=128)

    public String getCamPassword() {
        return this.camPassword;
    }
    
    public void setCamPassword(String camPassword) {
        this.camPassword = camPassword;
    }
    
    @Column(name="CAM_DEVID", length=32)

    public String getCamDevid() {
        return this.camDevid;
    }
    
    public void setCamDevid(String camDevid) {
        this.camDevid = camDevid;
    }
    
    @Column(name="CAM_CHANNEL", length=32)

    public String getCamChannel() {
        return this.camChannel;
    }
    
    public void setCamChannel(String camChannel) {
        this.camChannel = camChannel;
    }
   








}