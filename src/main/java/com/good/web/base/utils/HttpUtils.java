package com.good.web.base.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.net.httpserver.HttpExchange;

import java.util.HashMap;




/**
 *
 * <p>Title: </p>
 * <p>Description: http utils </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author LiLu
 * @version 1.0
 */
public class HttpUtils {

 
  private static final String URL_PARAM_CONNECT_FLAG = "&";

  private HttpUtils() {
  }

  /**
   * GET METHOD
   * @param strUrl String
   * @param map Map
   * @throws IOException
   * @return List
   */
  public static List<String> URLGet(String strUrl, Map<String, String> map) throws IOException {
    String strtTotalURL = "";
    List<String> result = new ArrayList<String>();
    if(strtTotalURL.indexOf("?") == -1) {
      strtTotalURL = strUrl + "?" + getUrl(map);
    } else {
      strtTotalURL = strUrl + "&" + getUrl(map);
    }
    URL url = new URL(strtTotalURL);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setUseCaches(false);
    HttpURLConnection.setFollowRedirects(true);
    BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream(),"utf8"));
    while (true) {
      String line = in.readLine();
      if (line == null) {
        break;
      }
      else {
    	  result.add(line);
      }
    }
    in.close();
    con.disconnect();
    return result;
  }

  /**
   * POST METHOD
   * @param strUrl String
   * @param content Map
   * @throws IOException
   * @return List
   */
  public static List<String> URLPost(String strUrl, Map<String, String> map,String decodeCharset)  {
	  	List<String> result = new ArrayList<String>(); 
	    String content = "";
	    content = getUrl(map);
	    String totalURL = null;
	    if(strUrl.indexOf("?") == -1) {
	      totalURL = strUrl + "?" + content;
	    } else {
	      totalURL = strUrl + "&" + content;
	    }
	    URL url;
		try {
			System.out.println(strUrl);
			url = new URL(strUrl);
		    HttpURLConnection con = (HttpURLConnection) url.openConnection();
		    con.setDoInput(true);
		    con.setDoOutput(true);
		    con.setAllowUserInteraction(false);
		    con.setUseCaches(false);
		    con.setRequestMethod("POST");
		    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset="+decodeCharset);
		    BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(con.
		        getOutputStream()));
		    bout.write(new String(content.getBytes(decodeCharset)));
		    bout.flush();
		    bout.close();
		    BufferedReader bin = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
		    
		   
		    while (true) {
		      String line = bin.readLine();
		      if (line == null) {
		        break;
		      }
		      else {
		    	  result.add(line); 
		    	  System.out.println(line);
		      }
		    }
		    con.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
	    return result;
  }

  /**
   * POST METHOD
   * @param strUrl String
   * @param content Map
   * @throws IOException
   * @return List
   */
  public static List<String> URLPostJson(String strUrl, String obj,String decodeCharset)  {
	  	List<String> result = new ArrayList<String>(); 
	    String content = obj;
	    String totalURL = null;
	    if(strUrl.indexOf("?") == -1) {
	      totalURL = strUrl + "?" + content;
	    } else {
	      totalURL = strUrl + "&" + content;
	    }
	    URL url;
		try {
			System.out.println(strUrl);
			url = new URL(strUrl);
		    HttpURLConnection con = (HttpURLConnection) url.openConnection();
		    con.setDoInput(true);
		    con.setDoOutput(true);
		    con.setAllowUserInteraction(false);
		    con.setUseCaches(false);
		    con.setRequestMethod("POST");
		    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset="+decodeCharset);
		    BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(con.
		        getOutputStream()));
		    bout.write(new String(content.getBytes(decodeCharset)));
		    bout.flush();
		    bout.close();
		    BufferedReader bin = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
		    
		   
		    while (true) {
		      String line = bin.readLine();
		      if (line == null) {
		        break;
		      }
		      else {
		    	  result.add(line); 
		    	  System.out.println(line);
		      }
		    }
		    con.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
	    return result;
  }
  
  /**
   * ���URL
   * @param map Map
   * @return String
   */
  private static String getUrl(Map<String, String> map) {
    if (null == map || map.keySet().size() == 0) {
      return ("");
    }
    StringBuffer url = new StringBuffer();
    Set keys = map.keySet();
    for (Iterator i = keys.iterator(); i.hasNext(); ) {
      String key = String.valueOf(i.next());
      if (map.containsKey(key)) {
    	 Object val = map.get(key);
    	 String str = val!=null?val.toString():"";
    	 try {
			str = URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        url.append(key).append("=").append(str).
            append(URL_PARAM_CONNECT_FLAG);
      }
    }
    String strURL = "";
    strURL = url.toString();
    if (URL_PARAM_CONNECT_FLAG.equals("" + strURL.charAt(strURL.length() - 1))) {
      strURL = strURL.substring(0, strURL.length() - 1);
    }
    return (strURL);
  }
  
  public static void parseGetParameters(HttpExchange exchange,String decodeCharset) throws UnsupportedEncodingException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		URI requestedUri = exchange.getRequestURI();
		String query = requestedUri.getRawQuery();
		parseQuery(query, parameters,decodeCharset);
		exchange.setAttribute("parameters", parameters);
	}
  
  public static void parsePostParameters(HttpExchange exchange,String decodeCharset) throws IOException {
		if ("post".equalsIgnoreCase(exchange.getRequestMethod())) {
			@SuppressWarnings("unchecked")
			Map<String, Object> parameters = (Map<String, Object>) exchange.getAttribute("parameters");
			InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), decodeCharset);
			BufferedReader br = new BufferedReader(isr);
			String query = br.readLine();
			parseQuery(query, parameters,decodeCharset);
		}
	}

  public static void parseQuery(String query, Map<String, Object> parameters,
			String decodeCharset) throws UnsupportedEncodingException {
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
	
	
}

