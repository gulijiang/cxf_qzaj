package com.qzaj.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class ResultSetTool {

	/**
	 * 获取Sql语句�?��的字符串，在原有字符串数组的每个字符串加�?,
	 * 
	 * @param strs
	 *            字符串数�?
	 * @return 字符�?
	 */
	public static String getSqlStr(String[] strs) {
		if (strs == null || strs.length < 1) {
			return null;
		}
		StringBuffer buf = new StringBuffer();
		for (String str : strs) {
			buf.append("'").append(str).append("',");
		}
		return buf.substring(0, buf.length() - 1).toString();
	}

	/**
	 * 获取Sql语句�?��的字符串，在原有字符串数组的每个字符串加�?,
	 * 
	 * @param list
	 *            字符串集�?
	 * @return 字符�?
	 */
	public static String getSqlStr(List<String> list) {
		if (list == null || list.isEmpty()) {
			return null;
		}
		StringBuffer buf = new StringBuffer();
		for (String str : list) {
			buf.append("'").append(str).append("',");
		}
		return buf.substring(0, buf.length() - 1).toString();
	}

	/**
	 * 将对象数组转换为对象List
	 * 
	 * @param objs
	 *            对象数组
	 * @return
	 */
	public static List<Object> array2List(Object[] objs) {
		if (objs == null || objs.length < 1) {
			return null;
		}
		List<Object> list = new LinkedList<Object>();
		for (int i = 0; i < objs.length; i++) {
			list.add(objs[i]);
		}
		return list;
	}

	/**
	 * 给定对象数组，以及一些�?，以及方法名称，获取到合乎条件的对象数组，可以用于DataGrid的getElementsByIds方法重载
	 * 例如：给定对象数组，在给定idcolumn的一些�?('1','2')等，在给定getMethod:"getUuid()"，则此方法会给出对象数组中，getUuid=1�?2的对�?
	 * 
	 * @param eles
	 *            对象数组
	 * @param ids
	 *            过滤的�?
	 * @param getMethod
	 *            过滤的方�?
	 * @return
	 */
	public static List<Object> getElementsByIds(Object[] eles, Object[] ids,
			String getMethod) {
		try {
			if (ids == null || ids.length < 1) {
				return null;
			}
			if (eles == null || eles.length < 1) {
				return null;
			}
			List<Object> idList = array2List(ids);
			List<Object> result = new LinkedList<Object>();
			for (int i = 0; i < eles.length; i++) {
				Object ele = eles[i];
				Method meth = ele.getClass().getMethod(getMethod, null);
				Object value = meth.invoke(ele, new Object[] {});
				if (idList.contains(value)) {
					result.add(ele);
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List result2BeanFormatDateTimeHM(ResultSet rs, Class beanClass)
			throws Exception {
		return result2BeanMethod(rs, beanClass, null, "yyyy-MM-dd HH:mm");
	}

	public static List result2BeanFormatDateTime(ResultSet rs, Class beanClass,
			HashMap<String, String> dateFormatMap, String defaultPattern)
			throws Exception {
		return result2BeanMethod(rs, beanClass, dateFormatMap, defaultPattern);
	}

	/**
	 * 将resultset转换成给定类型的对象集合
	 * 
	 * @param rs
	 *            数据�?
	 * @param beanClass
	 *            给定类型
	 * @return
	 * @throws Exception
	 */
	public static List result2Bean(ResultSet rs, Class beanClass)
			throws Exception {
		return result2BeanMethod(rs, beanClass, null, null);
	}

	private static Date parseDate(String source, String pattern)
			throws Exception {
		if (source == null || "".equals(source.trim())) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.parse(source);
	}

	public static HashMap<String, Object> result2map(ResultSet rs) throws Exception{
		HashMap<String, Object> map=new HashMap<String, Object>();
		rs.next();
		ResultSetMetaData meta = rs.getMetaData();
		for (int i = 1; i <= meta.getColumnCount(); i++) {
			map.put(meta.getColumnName(i).toLowerCase(), rs.getObject(i));
		}
		return map;
	}
	public static List<HashMap<String, Object>> result2List(ResultSet rs) throws Exception{
		List<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
		while(rs.next()){
		HashMap<String, Object> map=new HashMap<String, Object>();
		ResultSetMetaData meta = rs.getMetaData();
		for (int i = 1; i <= meta.getColumnCount(); i++) {
			map.put(meta.getColumnName(i).toLowerCase(), rs.getObject(i));
		}
		list.add(map);
		}
		return list;
	}
	private static List result2BeanMethod(ResultSet rs, Class beanClass,
			HashMap<String, String> dateFormatMap, String defaultDateFormat)
			throws Exception {
		Field[] flds = beanClass.getDeclaredFields();
		List<Object> result = new LinkedList<Object>();
		while (rs.next()) {
			ResultSetMetaData meta = rs.getMetaData();
			
			Object obj = beanClass.newInstance();
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				Field fld = null;
				Object data = null;
				Class cls = null;
				if (meta.getColumnType(i) == Types.VARCHAR) {
					data = rs.getString(i);
					cls = String.class;

					data = rs.getString(i);
					cls = String.class;

					if (defaultDateFormat != null
							&& !defaultDateFormat.equals("")) {
						try {
							Method meth = beanClass.getMethod("get"
									+ getCamel(meta.getColumnName(i)
											.toLowerCase()));
							Class resultType = meth.getReturnType();

							if (resultType == Date.class) {
								String pattern = defaultDateFormat;
								if (dateFormatMap != null
										&& !dateFormatMap.isEmpty()
										&& dateFormatMap.containsKey(meta
												.getColumnName(i))) {
									pattern = dateFormatMap.get(pattern);
								}
								data = parseDate((String) data, pattern);
								cls = Date.class;
							}
						} catch (Exception e) {
						}
					}

				} else if (meta.getColumnType(i) == Types.NUMERIC) {
					try {
						Method meth = beanClass
								.getMethod("get"
										+ getCamel(meta.getColumnName(i)
												.toLowerCase()));
						Class resultType = meth.getReturnType();
						if (resultType == Integer.class) {
							data = rs.getInt(i);
							cls = Integer.class;
						} else if (resultType == double.class) {
							data = rs.getDouble(i);
							cls = double.class;
						}else if(resultType == Double.class) {
							data = rs.getDouble(i);
							cls = Double.class;
						}else if (resultType == Float.class) {
							data = rs.getFloat(i);
							cls = Float.class;
						}else if (resultType == float.class) {
							data = rs.getDouble(i);
							cls = Double.class;
						}
						else {
							data = rs.getInt(i);
							cls = int.class;
						}
					} catch (Exception e) {
						continue;
					}
				} else if (meta.getColumnType(i) == Types.DATE
						|| meta.getColumnType(i) == Types.TIMESTAMP
						|| meta.getColumnType(i) == Types.TIME) {
					data = rs.getDate(i);
					cls = Date.class;
				} else if (meta.getColumnType(i) == Types.INTEGER)
				{
					data = rs.getInt(i);
					cls = Integer.class;
				}else if(meta.getColumnType(i) == Types.CHAR){
					data = rs.getObject(i);
					cls = String.class;
				}
				try {
					Method meth = beanClass.getMethod("set"
							+ getCamel(meta.getColumnName(i).toLowerCase()),
							cls);
					meth.invoke(obj, data);
					
					
				} catch (Exception e) {
					continue;
				}
			}
			result.add(obj);
		}
		return result;
	}

	private static String getCamel(String str) {
		StringBuffer buf = new StringBuffer();
		String[] arr = str.split("_");
		for (int i = 0; i < arr.length; i++) {
			buf.append(arr[i].substring(0, 1).toUpperCase()).append(
					arr[i].substring(1, arr[i].length()));
		}
		return buf.toString();
	}

	/**
	 * 将给定的对象集合，转换为List+Hashmap的组合，例如：对象中的方法为getUuid，则map的Key为uuid
	 * 
	 * @param list
	 * @return
	 */
	public static List<HashMap<String, Object>> entity2Map(List list) {
		if (list == null || list.isEmpty()) {
			return null;
		}
		List<HashMap<String, Object>> result = new LinkedList<HashMap<String, Object>>();
		for (Object entity : list) {
			HashMap<String, Object> map = new LinkedHashMap<String, Object>();
			Method[] meths = entity.getClass().getMethods();
			for (Method meth : meths) {
				String methName = meth.getName();
				if (methName.indexOf("get") < 0) {
					continue;
				}
				String key = methName.substring(3, 4).toLowerCase()
						+ methName.substring(4, methName.length());
				try {
					map.put(key, meth.invoke(entity, new Object[] {}));
				} catch (Exception e) {
					continue;
				}
			}
			result.add(map);
		}
		return result;
	}





	
}
