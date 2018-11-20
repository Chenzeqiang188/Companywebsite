package com.good.web.service.impl;


import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.web.base.utils.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.good.web.base.DataCenter;
import com.good.web.base.EntityManage;
import com.good.web.domain.GetItemLogs;

public class GainItemCodeServiceImpl {
	private EntityManage entityManage;
	private DataCenter dataCenter;
	private HttpServletResponse response;
	public void setEntityManage(EntityManage entityManage) {
		this.entityManage = entityManage;
	}
	public void setDataCenter(DataCenter dataCenter) {
		this.dataCenter = dataCenter;
	}
	
	
	/**
	 * @Title: getserverlist
	 *@Description: 获取区服列表
	 * @param @return
	 *@return JSONArray
	 * @author 李松茂   
	 * 2016-6-27 下午02:12:17
	 */
	public JSONArray getserverlist(){
		JSONArray arrList=new JSONArray();
		JSONObject jc=new JSONObject();
		String resultstring=JsonPostRequestUtil.loadJSON(PropertiesUtil.getValue("serrverurl"), jc.toJSONString());
		System.out.println(resultstring);
		if(!ValidateUtil.str_isEmpty(resultstring)){
			JSONObject result_data = JSONObject.parseObject(resultstring);
			int c=result_data.getInteger("code");
			if(c==0){
				JSONArray arr = result_data.getJSONArray("serv");
				if(arr!=null&&arr.size()>0){
					for(int i=0;i<arr.size();i++){
						JSONObject job=(JSONObject)arr.get(i);
						JSONObject obj=new JSONObject();
						obj.put("severid", job.get("serv_id"));
						obj.put("servername", job.get("serv_name"));
						arrList.add(obj);
					}
				}
			}
		}
		
		return arrList;
	}
	
	/**
	 * @Title: gainitmecode
	 *@Description: 世界ol获取礼包码
	 * @param @param request
	 * @param @param yzcode
	 * @param @param serverid
	 * @param @param actorid
	 * @param @param servername
	 * @param @param contentType
	 *@return void
	 * @author 李松茂   
	 * 2016-6-27 下午02:33:11
	 */
	public synchronized void gainitmecode(HttpServletRequest request,String yzcode,String serverid,
			String actorid,String servername,String contentType ){
		JSONObject jobj=new JSONObject();
		String message="";
		int result=-1;//失败
		String itemcode="";
		try {
			String yzmcode=(String)request.getSession().getAttribute("ccode");
			//验证数据
			if(ValidateUtil.str_isEmpty(yzcode)||!yzcode.equalsIgnoreCase(yzmcode)){
				message="请输入正确的验证码！";
			}else if(ValidateUtil.isNotEmpty(serverid)){
				message="请选择区服";
			}else if(!ValidateUtil.str_isNumber(serverid)){
				message="请选择正确的区服";
			}else if(ValidateUtil.isNotEmpty(actorid)||actorid.length()<6||actorid.length()>10||!ValidateUtil.str_ganitmecodeaccount(actorid)){
				message="请填写正确的玩家id";
			}else{
				//判断玩家是否已经领取过礼包
				String hql=" from GetItemLogs where actorid='"+actorid+"'";
				System.out.println("======hql========="+hql);
				List<GetItemLogs> itemlogsList=entityManage.findByHql(hql);
				if(itemlogsList!=null&&itemlogsList.size()>0){
					GetItemLogs itemlogs=itemlogsList.get(0);
					message="您已领取过该礼包码,您的礼包是："+itemlogs.getItemcode();
				}else{
					SimpleDateFormat fmth=new SimpleDateFormat("yyyy-MM-dd");
					//ip限制验证,同一个ip一天只能领取20个
					String ip=UrlUtils.getIpAddr(request);
					System.out.println("获取ip:"+ip);
					String hqlcount=" from GetItemLogs where  ip='"+ip
					+"' and DATE_FORMAT(createtime,'%Y-%m-%d')='"+fmth.format(new Date())+"'";
					int i=entityManage.getCountByHql(hqlcount);
					if(i>20){
						message="今天您已不能领取！";
					}else{
						//领取礼包
						Map<String, String> map=new HashMap<String, String>();
						map.put("do", "codeRequest");
						map.put("areaid", serverid);
						map.put("uid", actorid);
						String sign=MD5.toMD5((PropertiesUtil.getValue("key")+serverid+actorid)).toLowerCase();
						map.put("sign", sign);
						List<String> jsonreult=HttpUtils.URLGet(PropertiesUtil.getValue("gaincode"), map);
						System.out.println("游戏接口返回结果："+jsonreult);
						if(jsonreult!=null&&jsonreult.size()>0){
							String s=jsonreult.get(0);
							JSONObject robj=JSON.parseObject(s);
							int platrs = robj.getIntValue("code");
							if(platrs==0){
								//领取成功
								result=0;
								String code=robj.getString("msg");
								message="领取成功!礼包码："+code;
								itemcode=code;
								//保存礼包领取记录
								GetItemLogs gitemlogs= new GetItemLogs();
								gitemlogs.setActorid(actorid);
								gitemlogs.setCodetype(1);//礼包类型1、世界ol新手礼包
								gitemlogs.setGameid(1);//游戏id,1、世界ol
								gitemlogs.setServerid(Integer.parseInt(serverid));
								gitemlogs.setServername(servername);
								gitemlogs.setItemcode(itemcode);
								gitemlogs.setCreatetime(new Timestamp(System.currentTimeMillis()));
								gitemlogs.setIp(ip);
								entityManage.save(gitemlogs);
							}else{
								if(platrs==3){
									message="您已经领取过邀请码";
								}else{
									message="账号不存在或不满足领取条件！";
								}
							}
						}else{
							message="领取失败！";
						}
					}
				}
			}
		} catch (Exception e) {
			message="操作异常！请稍后再试！";
			System.out.println("==========操作异常=================");
			e.printStackTrace();
		}
		
		try {
			jobj.put("result", result);
			jobj.put("message", message);
			jobj.put("itemcode", itemcode);
			String CoinMap=jobj.toJSONString();
			//指定输出内容类型和编码  
			response.setContentType(contentType);
	        //获取输出流，然后使用  
	        PrintWriter out = response.getWriter();
	        //直接进行文本操作  
	        out.print(CoinMap);  
	        out.flush();  
	        out.close();  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
