package com.qzaj.action;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Timer;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.qzaj.service.Service;
import com.qzaj.util.Common;
@Component
@SuppressWarnings("all")
public class ReportAction extends ActionSupport{
	private Service service;
	public long synctime; //时间
	
	
	public void startsync(){
        Timer timer = new Timer();
        timer.schedule(new EvaluateActivityTimer(service, synctime*1000), 0, synctime*1000);
	}
	public void stopsync(){
		EvaluateActivityTimer.stop=true;
	}
	
	public Service getService() {
		return service;
	}
	@Resource
	public void setService(Service service) {
		this.service = service;
	}
	public long getSynctime() {
		return synctime;
	}
	public void setSynctime(long synctime) {
		this.synctime = synctime;
	}
	
}
