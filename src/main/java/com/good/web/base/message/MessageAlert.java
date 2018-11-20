package com.good.web.base.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.good.web.base.utils.ValidateUtil;

public class MessageAlert {
	public static void println(HttpServletResponse response,String message){
		try {
		   response.setCharacterEncoding("utf-8");
		   response.getWriter().write(message);
		} catch (IOException e) {
			 e.printStackTrace();
		}
    	
	}
	public static void Alert(HttpServletResponse response,String message){
		try {
		   response.setCharacterEncoding("utf-8");
//		   response.getWriter().write("<script>alert("+message+");</script>");
		   PrintWriter out = response.getWriter();
		   out.print("<script>alert("+message+");</script>");
		   out.flush();
		   out.close();
		} catch (IOException e) {
			 e.printStackTrace();
		}
    	
	}
	public static void println(HttpServletResponse response,JSON obj){
		try {
		   response.setCharacterEncoding("utf-8");
		   response.getWriter().write(JSON.toJSONString(obj));
		} catch (IOException e) {
			 e.printStackTrace();
		}
    	
	}
	/**
	 * 获取json参数
	 * @param request
	 * @return
	 */
	public static JSONObject Prarm2Json(HttpServletRequest request ){
		Map prarmMap=request.getParameterMap();
		if (prarmMap!=null&&prarmMap.size()>0){
			String prarm=(String) prarmMap.keySet().toArray()[0];
			JSONObject jsonObject =JSONObject.parseObject(prarm);
			return jsonObject;
		}else{
			return null;
		}
		 
	}
	
	/**
	 * 获取json参数
	 * @param request
	 * @return
	 */
	public static String Prarm2StringOutWithSig(HttpServletRequest request ){
		Map prarmMap=request.getParameterMap();
		if (prarmMap!=null&&prarmMap.size()>0){
			String prarm=(String) prarmMap.keySet().toArray()[0];
			if(!ValidateUtil.str_isEmpty(prarm)){
				String index = ",\"sig\":\"";
				int indexs = prarm.indexOf(index);
				String singstring = prarm.substring(prarm.indexOf(index), indexs+41);
				return prarm.replaceAll(singstring, "");
			}
		}
			return null;
	}
public static void main(String [] args){
	//System.out.println(java.net.URLEncoder.encode("{(\"111ww\")}"));
	String prarm ="{\"cmdId\":10001,\"data\":{\"serverid\":1,\"actorid\":1,\"accountid\":1,\"actorname\":\"测试角色名\",\"questiontype\":1,\"sendtime\":1425623610023,\"theme\":\"QQ:381405335\",\"content\":\"测试内容\"},\"sig\":\"D556D1C4A04536C1053C1333BD5192BF\"}";
	String index = ",\"sig\":\"";
	int indexs = prarm.indexOf(index);
	String singstring = prarm.substring(prarm.indexOf(index), indexs+41);
	System.out.println(singstring);
	System.out.println(prarm.replaceAll(singstring, ""));
}
}
