package com.qzaj.service;

import javax.jws.WebResult;
import javax.jws.WebService;


@WebService
public interface IService {

	//获取企业数据
	public @WebResult(name = "String")
	String getCompdata();
	
	//企业数据插入  第二种情况
//	public @WebResult(name = "String")
//	String insertCompData(@WebParam(name = "content")String content);
}
