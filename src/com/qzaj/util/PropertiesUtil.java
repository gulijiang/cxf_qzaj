package com.qzaj.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * 读取配置文件properties工具类
 *
 */
public class PropertiesUtil {
	
	/**
	 * 读取静态配置文件，放入内存中 
	 */
	private static final Map<String,String> staticsData = new HashMap<String,String>();
	
	/**
	 * 资源文件配置路径
	 */
	private static final String STATICFILEPATH = "/statics.properties";
	
	
	static{
		readResourceInMap(STATICFILEPATH, staticsData);
	}
	
	/**
	 * 根据key来获取statics文件中相应的value
	 * 
	 * @param key 键名
	 * @return
	 */
	public static String readValue(String key) {
		if (null != staticsData)
			return staticsData.get(key);
		return readValue(STATICFILEPATH, key);
	}	
	
	
	/**
	 * 从指定配置文件里读取具体的key值
	 * 
	 * @param path 配置文件地址(如：/statics.properties)
	 * @param key 键名
	 * @return
	 */
	public static void readResourceInMap(String filepath, Map<String,String> stataMap) {
		InputStream stream = null;
		try {
			//将文件以流的形式读入
			stream = PropertiesUtil.class.getResourceAsStream(filepath);
			//定义属性对象
			Properties props = new Properties();
			//获取属性列表
			props.load(stream);
			
			// 返回Properties中包含的key-value的Set视图   
	        Set<Entry<Object, Object>> set = props.entrySet();  
	        
	        // 返回在此Set中的元素上进行迭代的迭代器   
	        Iterator<Entry<Object, Object>> it = set.iterator();   
	        // 循环取出key-value   
	        while (it.hasNext()) {   
	            Entry<Object, Object> entry = it.next();   
	            stataMap.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
	        } 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//关闭流
			try {
				if (null != stream)
					stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 从指定配置文件里读取具体的key值
	 * 
	 * @param path 配置文件地址(如：/statics.properties)
	 * @param key 键名
	 * @return
	 */
	public static String readValue(String path, String key) {
		InputStream stream = null;
		String value = null;
		try {
			//将文件以流的形式读入
			stream = PropertiesUtil.class.getResourceAsStream(path);
			//定义属性对象
			Properties props = new Properties();
			//获取属性列表
			props.load(stream);
			
			value = props.getProperty(key);
			return value;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//关闭流
			try {
				if (null != stream)
					stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
}
