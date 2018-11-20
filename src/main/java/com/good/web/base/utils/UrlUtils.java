package com.good.web.base.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class UrlUtils {
	
	/**
	 * 
	 * Function name:getRequestUriValue
	 * Description: 解析uri字串，获得数据
	 * @param uri：字串
	 * @return：数据对
	 */
	public static HashMap<String,String> getRequestUriValue(String uri){
		HashMap<String,String> attributes = new HashMap<String,String>();
		int beginIndex = uri.indexOf("?");
		if(beginIndex!=-1){
			String body = uri.substring(beginIndex+1);
			String[] strs = body.split("&");
			for(String keyValues : strs){
				String[] attribute = keyValues.split("=");
				if(attribute.length > 2){
					continue;
				}
				if(attribute.length == 1){
					String key = attribute[0];
					String value = "";
					attributes.put(key, value);
				}
				if(attribute.length == 2){
					String key = attribute[0];
					String value = attribute[1];
					attributes.put(key, value);
				}
			}
		}
		return attributes;
	}
	
	public static void parseGetParameters(HttpServletRequest request,String decodeCharset) throws UnsupportedEncodingException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String query = request.getQueryString();
		parseQuery(query, parameters,decodeCharset);
		request.setAttribute("parameters", parameters);
	}

	public static void parsePostParameters(HttpServletRequest request,String decodeCharset) throws IOException {
		if ("post".equalsIgnoreCase(request.getMethod())) {
			@SuppressWarnings("unchecked")
			Map<String, Object> parameters = (Map<String, Object>) request.getAttribute("parameters");
			InputStreamReader isr = new InputStreamReader(request.getInputStream(), decodeCharset);
			BufferedReader br = new BufferedReader(isr);
			String query = br.readLine();
			parseQuery(query, parameters,decodeCharset);
		}
	}

	@SuppressWarnings("unchecked")
	public static void parseQuery(String query, Map<String, Object> parameters ,String decodeCharset)
			throws UnsupportedEncodingException {
		if (query != null) {
			String pairs[] = query.split("[&]");
			for (String pair : pairs) {
				String param[] = pair.split("[=]");

				String key = null;
				String value = null;
				if (param.length > 0) {
					key = URLDecoder.decode(param[0], decodeCharset);
				}
				if (param.length > 1) {
					value = URLDecoder.decode(param[1], decodeCharset);
				}
				if (parameters.containsKey(key)) {
					Object obj = parameters.get(key);
					if (obj instanceof List<?>) {
						List<String> values = (List<String>) obj;
						values.add(value);
					} else if (obj instanceof String) {
						List<String> values = new ArrayList<String>();
						values.add((String) obj);
						values.add(value);
						parameters.put(key, values);
					}
				} else {
					parameters.put(key, value);
				}
			}
		}
	}
	/***
	 * 获取请求的ip地址
	* @author:lisongmao
	* @Description:  
	* @param @param request
	* @param @return    
	* @return String    
	* @throws
	 */
	public static  String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	

}
