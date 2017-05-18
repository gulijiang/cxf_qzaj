package com.qzaj.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	//将格林时间的毫秒转化成字符串
	public String transDate(int s){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date(s*1000L));
		return date;
	}
	
	
	//判断当前时间是否超过传入时间24小时,超过返回true，不超过返回fasle
	public static boolean judgmentDate(String date1)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = sdf.parse(date1);
		Date end = new Date();
		long cha = end.getTime() - start.getTime();
		if (cha < 0) {
			return false;
		}
		double result = cha * 1.0 / (1000 * 60 * 60);
		if (result > 24) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		boolean a = DateUtil.judgmentDate("2016-08-23 14:15:56");
		System.out.println(a);
	}
}
