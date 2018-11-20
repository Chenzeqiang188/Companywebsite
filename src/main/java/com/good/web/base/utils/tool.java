package com.good.web.base.utils;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.good.web.base.message.MessageAlert;
/**
 * 子平台工具
 * @author max
 *
 */
public class tool {
	/**
	 * 验证MD5
	 * @param prarm
	 * @return
	 */
	public static boolean verifyMD5(JSONObject prarm,HttpServletRequest request){
		/*String sig = prarm.getString("sig");
		if(sig==null||sig.trim().equals("")){
			return false;
		}
		prarm.remove("sig");
		System.out.println("beforsig====="+prarm.toJSONString());
		String platsig = Md5Encrypt.md5(MessageAlert.Prarm2StringOutWithSig(request));//+SysEvn.KEY);
		System.out.println("platsig===="+platsig);
		if(sig.equals(platsig)){
			return true;
		}*/
		return false;
				
	}
	
	/**
	 * 拼接加密参数
	 * @param data
	 * @return
	 */
	public static JSONObject splicePrarm(JSONObject data){
	/*	String sig = Md5Encrypt.md5(data.toJSONString());//+SysEvn.KEY);
		data.put("sig", sig);
		return data;*/
	return null;
	}
      /**
	 * 有参数的HTTP POST请求
	 * @param urlPath HTTP接口
	 * @param obj 请求参数为JSON对象
	 * @return
	 */
	  public static String loadJSON(JSONObject data) {
		  return  JsonPostRequestUtil.loadJSON(PropertiesUtil.getValue("websiteurl"), splicePrarm(data).toJSONString());
		
	}
}
