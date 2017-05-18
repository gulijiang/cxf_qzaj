package com.qzaj.test;

import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;


public class AnotherClient {
//	public static void main(String[] args) throws Exception {
//		String addr = "http://localhost:8888/CXF_DATA/IService?wsdl";
//		//String addr = "http://172.27.30.99:8081/CXF_service/IService?wsdl";
//		DynamicClientFactory dcf = DynamicClientFactory.newInstance();
//		Client client =  dcf.createClient(addr);
//		JSONObject inobj = new JSONObject();
//		/*inobj.put("compid", "3");
//		inobj.put("sourceid", "15");
//		inobj.put("sourceattr", "16");*/
//		inobj.put("compname", "衢州杭氧气体有限公司");
//		inobj.put("sourcename", "储罐1");
//		inobj.put("sourceattrname", "温度");
//		inobj.put("attrval", "170");
//		String content = inobj.toString();
//		//第一种情况 compid: "3", sourceid: "15", sourceattr: "16", attrval: "148"
//		//第二种情况 compname："衢州杭氧气体有限公司",sourcename:"储罐1",sourceattrname:"温度",attrval: "148"
//		Object[] replys = null;
//		try {
//			//replys = client.invoke("compdata",new Object[]{content});
//			replys = client.invoke("insertCompData",new Object[]{content});
//			System.out.println(replys[0].toString());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public static void main(String[] args) throws Exception {
//		String addr = "http://127.0.0.1:8899/Customs/esb/webservice/CustomsService?wsdl";
//		//String addr = "http://172.27.30.99:8081/CXF_service/IService?wsdl";
//		DynamicClientFactory dcf = DynamicClientFactory.newInstance();
//		Client client =  dcf.createClient(addr);
//		JSONObject inobj = new JSONObject();
//		inobj.put("account", "super");
//		inobj.put("password", "111111");
//		String content = inobj.toString();
//		//第一种情况 compid: "3", sourceid: "15", sourceattr: "16", attrval: "148"
//		//第二种情况 compname："衢州杭氧气体有限公司",sourcename:"储罐1",sourceattrname:"温度",attrval: "148"
//		Object[] replys = null;
//		try {
//			//replys = client.invoke("compdata",new Object[]{content});
//			replys = client.invoke("login",new Object[]{content});
//			System.out.println(replys[0].toString());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		/*long a = (new Date().getTime() - 1461054095)/1000L;
		System.out.println(a);*/
		String desc = "空压机电机运行信号(高压柜)";
	}
}	
