package com.qzaj.serviceImpl;

import java.util.List;

import javax.jws.WebService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qzaj.entity.TbComp;
import com.qzaj.service.IService;
import com.qzaj.service.Service;


@WebService(endpointInterface="com.qzaj.service.IService",serviceName="serviceImpl",targetNamespace="http://service.qzaj.com/")
public class ServiceImpl implements IService {
	//private Service service;
	
	@Override
	public String getCompdata() {
//		service.setEntity(TbComp.class);
//		List<TbComp> compList = service.findAll();
//		JSONArray resultArray = new JSONArray();
//		for (int i = 0,size = compList.size(); i < size; i++) {
//			TbComp tbComp = compList.get(i);
//			JSONObject compJson = new JSONObject();
//			compJson.put("id", tbComp.getCompId());
//			compJson.put("compname_name", tbComp.getCompName());
//			resultArray.add(compJson);
		//return resultArray.toString();
		return "";
	}


	
}
