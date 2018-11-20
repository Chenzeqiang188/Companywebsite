package com.good.web.base.utils;

import java.io.FileReader;
import java.util.Properties;
/**
 * 
 * @Title:
 * @Description 读取properties文件
 * @author  李松茂
 *   2016-6-14 下午02:41:24
 */
public class PropertiesUtil {
	private PropertiesUtil(){}
	private static Properties prop = null;
	static{
		try {
			prop = new Properties();
			prop.load(new FileReader(PropertiesUtil.class.getClassLoader().getResource("config.properties").getPath()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static String getValue(String key){
		return prop.getProperty(key);
	}
}
