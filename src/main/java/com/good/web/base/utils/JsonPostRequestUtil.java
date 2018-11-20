package com.good.web.base.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;
/**
 * 
 * TODO  (HTTP POST请求 )
 * @date    2014-3-6 下午02:19:04 
 * @author  mazhiyuan
 * @version 1.0.0
 */
public class JsonPostRequestUtil {
	/**
	 * 有参数的HTTP POST请求
	 * @param urlPath HTTP接口
	 * @param obj 请求参数为JSON对象
	 * @return
	 */
	  public static String loadJSON(String urlPath,String obj) {
		  System.out.println(System.currentTimeMillis()+"==========请求地址:"+urlPath+"  参数："+obj);
		  String jsonData = null;
	        try {
	            //创建连接
	            URL url = new URL(urlPath);
	            HttpURLConnection connection = (HttpURLConnection) url
	                    .openConnection();
	            connection.setDoOutput(true);
	            connection.setDoInput(true);
	            connection.setRequestMethod("POST");
	            connection.setUseCaches(false);
	            connection.setInstanceFollowRedirects(true);
	            connection.setRequestProperty("Content-Type",
	                    "application/x-www-form-urlencoded;charset=utf-8");
	            connection.connect();
	            //POST请求
	            DataOutputStream out = new DataOutputStream(
	                    connection.getOutputStream());
//	            out.writeBytes(obj.toString());
	            out.write(obj.getBytes("utf-8"));
	            out.flush();
	            out.close();
	            //读取响应
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    connection.getInputStream(),"utf-8"));
	            String lines;
	            StringBuffer sb = new StringBuffer("");
	            while ((lines = reader.readLine()) != null) {
//	                lines = new String(lines.getBytes(), "utf-8");
	                sb.append(lines);
	            }
	            jsonData = sb.toString();
	            reader.close();
	            // 断开连接
	            connection.disconnect();
	        } catch (MalformedURLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        System.out.println("==========结果:"+jsonData);
	        return jsonData;
	    }
	  
	  public static void main(String[] args) {
       JSONObject object = new JSONObject();
       object.put("cmdId", 10001);
     String data =	tool.loadJSON(object);
     System.out.println(data);
   }
}
