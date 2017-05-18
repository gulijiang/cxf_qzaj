package com.qzaj.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.qzaj.entity.TbComp;
import com.qzaj.entity.TbPoint;
import com.qzaj.entity.TbProduct;
import com.qzaj.entity.TbTag;
import com.qzaj.service.Service;
import com.qzaj.util.Common;
import com.qzaj.util.ResultSetTool;

@Component
@SuppressWarnings("all")
public class InterFaceAction extends ActionSupport {
	private Service service;
	private String comp;//企业ID
	private String point;//危险源ID
	private String tags;//监测位号列表
	//获取企业信息
	public void getCompdata() {
		service.setEntity(TbComp.class);
		List<TbComp> compList = service.findAll();
		JSONArray resultArray = new JSONArray();
		for (int i = 0,size = compList.size(); i < size; i++) {
			TbComp tbComp = compList.get(i);
			JSONObject compJson = new JSONObject();
			compJson.put("id", tbComp.getCompId());
			compJson.put("compname_name", tbComp.getCompName());
			resultArray.add(compJson);
		}
		Common.write(resultArray.toString());
	}
	
	//根据企业ID获取危险源信息
	public void getPointdata(){
		service.setEntity(TbPoint.class);
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<TbPoint> list = new ArrayList<TbPoint>();
		if(null == comp || comp.isEmpty()){
			list = service.findAll();
		}else{
			map.put("compId", comp);
			list = service.getListByProperty(map, null, null);
		}
		JSONArray resultArray = new JSONArray();
		for (int i = 0,size = list.size(); i < size; i++) {
			TbPoint tbPoint = list.get(i);
			JSONObject pointJson = new JSONObject();
			pointJson.put("id", tbPoint.getPointId());
			pointJson.put("name", tbPoint.getPointName());
			pointJson.put("compId", tbPoint.getCompId());
			resultArray.add(pointJson);
		}
		Common.write(resultArray.toString());
	}
	
	//根据危险源ID获取监测位号信息
	public void getTagdata(){
		service.setEntity(TbTag.class);
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<TbTag> list = new ArrayList<TbTag>();
		if(null == point || point.isEmpty()){
			//glj update
			map.put("pointId", 5);
			list = service.getListByProperty(map, null, null);
			//list = service.findAll();
		}else{
			map.put("pointId", point);
			list = service.getListByProperty(map, null, null);
		}
		JSONArray resultArray = new JSONArray();
		for (int i = 0,size = list.size(); i < size; i++) {
			TbTag tbTag = list.get(i);
			JSONObject tagJson = new JSONObject();
			tagJson.put("point", tbTag.getPointId());
			tagJson.put("tagname", tbTag.getTagName());
			tagJson.put("des", tbTag.getTagTank() + tbTag.getTagSign());
			tagJson.put("unit", tbTag.getTagUnit());
			tagJson.put("decimal", tbTag.getTagDecimal());
			tagJson.put("llimit", tbTag.getTagLlimit());
			tagJson.put("hlimit", tbTag.getTagHlimit());
			tagJson.put("lwarn", tbTag.getTagLwarn());
			tagJson.put("hwarn", tbTag.getTagHalarm());
			tagJson.put("lalarm", tbTag.getTagLalarm());
			tagJson.put("halarm", tbTag.getTagHalarm());
			resultArray.add(tagJson);
		}
		Common.write(resultArray.toString());
	}
	
	//根据监测位号获取实时数据
	public void getRealdata(){
		service.setEntity(TbTag.class);
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<TbTag> list = new ArrayList<TbTag>();
		if(null == tags || tags.isEmpty()){
			//glj update
			map.put("pointId", 5);
			list = service.getListByProperty(map, null, null);
			//list = service.findAll();
		}else{
			map.put("tagName_in", ResultSetTool.getSqlStr(tags.split(",")));
			list = service.getListByProperty(map, null, null);
		}
		JSONArray resultArray = new JSONArray();
		for (int i = 0,size = list.size(); i < size; i++) {
			TbTag tbTag = list.get(i);
			JSONObject tagJson = new JSONObject();
			tagJson.put("tagname", tbTag.getTagName());
			tagJson.put("value", tbTag.getTagValue());
			tagJson.put("stamp", tbTag.getTagTime().getTime());
			tagJson.put("alert", tbTag.getTagAlarm());
			resultArray.add(tagJson);
		}
		Common.write(resultArray.toString());
	}
	
	//根据企业获取生产状态
	public void getProductData(){
		service.setEntity(TbProduct.class);
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<TbProduct> list = new ArrayList<TbProduct>();
		if(null == comp || comp.isEmpty()){
			list = service.findAll();
		}else{
			map.put("compId", comp);
			list = service.getListByProperty(map, null, null);
		}
		JSONArray resultArray = new JSONArray();
		for (int i = 0,size = list.size(); i < size; i++) {
			TbProduct tbProduct = list.get(i);
			JSONObject proJson = new JSONObject();
			proJson.put("productId", tbProduct.getProductId());
			proJson.put("productName", tbProduct.getProductDesc());
			proJson.put("productFlag", tbProduct.getProductName());
			Integer state = tbProduct.getProductState();
			if(state == 1){
				proJson.put("productDes","开车");
			}else{
				proJson.put("productDes","停车");
			}
			proJson.put("state",state);
			proJson.put("compId",tbProduct.getCompId());
			resultArray.add(proJson);
		}
		Common.write(resultArray.toString());
	}
	
	
	
	
	public Service getService() {
		return service;
	}
	@Resource
	public void setService(Service service) {
		this.service = service;
	}

	public String getComp() {
		return comp;
	}

	public void setComp(String comp) {
		this.comp = comp;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
}
