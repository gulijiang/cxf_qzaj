package com.qzaj.util;

import net.sf.json.JSONObject;

/**
 * 返回json
 * @author glj
 *
 */
public class ResultJsonUtil {
	/**
	 * 获取成功json
	 * @param msg
	 * @return
	 */
	public static JSONObject getSuccessJson(String msg){
		JSONObject resultJson = new JSONObject();
		resultJson.put(CXFConstants.result_code, CXFConstants.result_success_code);
		resultJson.put(CXFConstants.result_msg, msg);
		return resultJson;
	}
	
	/**
	 * 获取成功json
	 * @param msg
	 * @return
	 */
	public static JSONObject getSuccessJson(){
		JSONObject resultJson = new JSONObject();
		resultJson.put(CXFConstants.result_code, CXFConstants.result_success_code);
		resultJson.put(CXFConstants.result_msg, CXFConstants.result_success_msg);
		return resultJson;
	}
	
	
	/**
	 * 获取成功json
	 * @param msg
	 * @return
	 */
	public static JSONObject getFailJson(){
		JSONObject resultJson = new JSONObject();
		resultJson.put(CXFConstants.result_code, CXFConstants.result_fail_code);
		resultJson.put(CXFConstants.result_msg, CXFConstants.result_fail_msg);
		return resultJson;
	}
	
	
	/**
	 * 获取成功json
	 * @param msg
	 * @return
	 */
	public static JSONObject getFailJson(String msg){
		JSONObject resultJson = new JSONObject();
		resultJson.put(CXFConstants.result_code, CXFConstants.result_fail_code);
		resultJson.put(CXFConstants.result_msg, msg);
		return resultJson;
	}
}
