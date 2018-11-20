package com.good.web.base.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class SdkTool {
	
	/**
	 * @Title: register
	 *@Description: SDK注册
	 * @param @param username
	 * @param @param password
	 * @param @return
	 *@return List<String>
	 * @author 李松茂   
	 * 2016-5-25 下午05:00:38
	 */
	public static List<String> register(String username,String password){
		String pwd= MD5.toMD5(password);
		int gameId = Integer.parseInt(PropertiesUtil.getValue("gameId"));
		int platformId = Integer.parseInt(PropertiesUtil.getValue("platformId"));
		int channel = Integer.parseInt(PropertiesUtil.getValue("channel"));
		int subchannel = Integer.parseInt(PropertiesUtil.getValue("subchannel"));
		String publicKey = PropertiesUtil.getValue("publicKey");
		String sdkUrl = PropertiesUtil.getValue("sdkUrl");
		Integer registact=2005;
		JSONObject jobj = new JSONObject();
		jobj.put("gameId", gameId);
		jobj.put("platformId", platformId);
		jobj.put("act", registact);
		jobj.put("username", username);
		jobj.put("verifyInfo", pwd);
		jobj.put("mobilePhone", "");
		jobj.put("ip", "");
		jobj.put("client", "web");
		jobj.put("os", "pc");
		jobj.put("channel", channel);
		jobj.put("childChannel", subchannel);
		jobj.put("regType", 6);//web注册
		jobj.put("realName", "");
		jobj.put("idCardNo", "");
		String signStr = "publicKey="+publicKey+"&gameId="+gameId+"&platformId="+platformId+"&act="+registact+"&username="+username
				+"&verifyInfo="+pwd+"&mobilePhone=&ip=&client=web&os=pc&channel="+channel+"&childChannel="+subchannel
				+"&regType=6&realName=&idCardNo=";
		System.out.println(signStr);
		String sign = MD5.toMD5(signStr);
		System.out.println(sign+"==============================");
		jobj.put("sign", sign);
		Map<String,String> map = new HashMap<String,String>();
		map.put("data", jobj.toJSONString());
		return HttpUtils.URLPost(sdkUrl, map, "utf-8");
	}
	
	/***
	 * 
	 * @Title: login
	 *@Description:SDK登陆
	 * @param @param username
	 * @param @param password
	 * @param @param isAutologin
	 * @param @return
	 *@return List<String>
	 * @author 李松茂   
	 * 2016-5-25 下午05:03:37
	 */
	public static List<String> login(String username,String password,boolean isAutologin){
		String psw = isAutologin ? password : MD5.toMD5(password);
		int gameId = Integer.parseInt(PropertiesUtil.getValue("gameId"));
		int platformId = Integer.parseInt(PropertiesUtil.getValue("platformId"));
		int channel = Integer.parseInt(PropertiesUtil.getValue("channel"));
		int subchannel = Integer.parseInt(PropertiesUtil.getValue("subchannel"));
		String publicKey = PropertiesUtil.getValue("publicKey");
		String sdkUrl = PropertiesUtil.getValue("sdkUrl");
		Integer loginact=1001;
		JSONObject jobj = new JSONObject();
		jobj.put("gameId", gameId);
		jobj.put("platformId", platformId);
		jobj.put("act", loginact);
		jobj.put("username", username);
		jobj.put("verifyInfo", psw);
		jobj.put("ip", "");
		jobj.put("client", "web");
		jobj.put("os", "pc");
		jobj.put("channel", channel);
		jobj.put("childChannel", subchannel);
		jobj.put("version", 10001);
		String signStr = "publicKey="+publicKey+"&gameId="+gameId+"&platformId="+platformId+"&act="+loginact+"&username="+username
				+"&verifyInfo="+psw+"&ip=&client=web&os=pc&version=10001&channel="+channel+"&childChannel="+subchannel;
		String sign = MD5.toMD5(signStr);
		jobj.put("sign", sign);
		Map<String,String> map = new HashMap<String,String>();
		map.put("data", jobj.toJSONString());
		return HttpUtils.URLPost(sdkUrl, map, "utf-8");
	}
	
	/**
	 * @Title: forgetPsw
	 *@Description: SDK 密码找回
	 * @param @param username
	 * @param @param email
	 * @param @return
	 *@return List<String>
	 * @author 李松茂   
	 * 2016-5-25 下午05:39:23
	 */
	public static List<String> forgetPsw(String username,String email){
		JSONObject jobj = new JSONObject();
		int gameId = Integer.parseInt(PropertiesUtil.getValue("gameId"));
		int platformId = Integer.parseInt(PropertiesUtil.getValue("platformId"));
		int channel = Integer.parseInt(PropertiesUtil.getValue("channel"));
		int subchannel = Integer.parseInt(PropertiesUtil.getValue("subchannel"));
		String publicKey = PropertiesUtil.getValue("publicKey");
		String sdkUrl = PropertiesUtil.getValue("sdkUrl");
		jobj.put("gameId", gameId);
		jobj.put("platformId", platformId);
		jobj.put("act", 3005);
		jobj.put("username", username);
		jobj.put("verifyInfo", email);
		jobj.put("publicKey", publicKey);
		String signStr = "publicKey="+publicKey+"&gameId="+gameId+"&platformId="+platformId+"&act=3005&username="+username
				+"&verifyInfo="+email;
		String sign = MD5.toMD5(signStr);
		jobj.put("sign", sign);
		Map<String,String> map = new HashMap<String,String>();
		map.put("data", jobj.toJSONString());
		return HttpUtils.URLPost(sdkUrl, map, "utf-8");
	}
	
	
}
