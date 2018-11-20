package com.good.web.base.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * TODO  参数验证工具类
 * @date    2013-11-8 上午11:41:18 
 * @author  liujinxin
 * @version 1.0.0
 *
 */
//@Entity
public class ValidateUtil {
	public static boolean str_isEmpty(String str)
	{
		if(str==null||"".equals(str))
			return true;
		return false;
	}
	
	public static boolean str_isNum(String str){
		if(str==null||"".equals(str.trim()))
			return false;
		Pattern p = Pattern.compile("-?[0-9]+$");
		Matcher m = p.matcher(str);   
		boolean b = m.matches();   
		return b; 
	}
	
	public static String str_Filter(String str){
		//過濾過程待完成
		return str; 
	}
	
	// 过滤所有标签,适用于标题过滤
	public static String filterAllTag ( String str ) {
		if ( str == null )return null;
		str = str.replaceAll("\n", "");
		str = str.replaceAll("\r", "");
		return str.replaceAll( "</?\\w*>", "" );
		
	}
	
	/** 时间转换成字符串
	 * type = "yyyy[/|-]MM[/|-]dd HH:mm:ss"
	 * type 为空值时输出与当前时间的隔离
	 */
	public static String date2Str ( Date date, String type ) {
		if ( date == null )
			return null;
		if ( ! str_isEmpty ( type ) ) {
			SimpleDateFormat df = new SimpleDateFormat( type );
			return df.format( date );
		}
		Date now = new Date();
		long time = now.getTime();
		long datetime = date.getTime();
		
		long timecount = time - datetime;
		if ( time - datetime > 60000 * 60 * 24 * 7 ) 
			return "一星期前";
		else if ( time - datetime > 60000 * 60 * 24 * 3 ) 
			return "三天前";
		else if ( time - datetime > 60000 * 60 * 24 ) 
			return "一天前";
		else if ( time - datetime > 60000 ) 
			return "一分钟前";
		else 
			return "刚刚";
	}
	
	// 只过滤<script>标签
	public static String filterHtmlTag(String str){
		if ( str == null )return null;
		str = str.replaceAll("<script", "&lt;script");
		str = str.replaceAll("</script", "&lt;/script");
		str = str.replaceAll("%26", "&");
		str = str.replaceAll("%3D", "=");
		return str;
	}
	
	 /**   
	 *   判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符）   
	 *   @param   char   c,   需要判断的字符   
	 *   @return   boolean,   返回true,Ascill字符   
	 */
	public static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}

	/**   
	 *   得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1   
	 *   @param   String   s   ,需要得到长度的字符串   
	 *   @return   int,   得到的字符串长度   
	 */
	public static int length(String s) {
		if( s == null )
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}   
	
	public static boolean isEmailFormat(String email){  
		if(email==null) return false;
        boolean isExist = false;   
        Pattern p = Pattern.compile("[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");  
        // Pattern p = Pattern.compile("\\w+.{0,1}+@(\\w+.)+[a-z]{2,3}");  
        Matcher m = p.matcher(email);   
        boolean b = m.matches();   
        if(b) {   
            System.out.println("��Ч�ʼ���ַ");   
            isExist=true;   
        } else {   
            System.out.println("��Ч�ʼ���ַ");   
        }   
        return isExist;   
    }  
	public static boolean str_isChinese(String str){
		if(str==null||"".equals(str.trim()))
			return false;
		if(str.length()<3||str.length()>64)
			return false;
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]+");
		Matcher m = p.matcher(str);   
		boolean b = m.matches();   
		return b; 
	}
	/**
	 * 验证金额
	 * @param str
	 * @return
	 */
	public static boolean str_isAmount(String str){
		if(str==null||"".equals(str.trim()))
			return false;
		Pattern p = Pattern.compile("^(([1-9]\\d{0,9})|0)(\\.\\d{1,2})?$");
		Matcher m = p.matcher(str);   
		boolean b = m.matches();   
		return b; 
	}
	
	/**
	 * 账号平台账号验证
	 * @param str
	 * @return
	 */
	public static boolean str_account(String str){
		if(str==null||"".equals(str.trim()))
			return false;
		Pattern p = Pattern.compile("[gG][dD][0-9]+$");
		Matcher m = p.matcher(str);   
		boolean b = m.matches();   
		return b; 
	}
	
	
	
	
	
	/**
	 * 验证字符串是否是由数字和字母组成的字符串
	 * @param str
	 * @return
	 */
	public static boolean str_isNumAndAlphabet(String str){
		if(str==null||"".equals(str.trim()))
			return false;
		Pattern p = Pattern.compile("^[A-Za-z0-9]+$");
		Matcher m = p.matcher(str);   
		boolean b = m.matches();   
		return b; 
	}
	
	
	/** 验证是否sql注射 */
	public static boolean isSQLInject(String s) {
		Matcher matcher = sqlPattern.matcher(s);
		return matcher.find();
	}
	
	private static Pattern sqlPattern = Pattern
	.compile(
			"(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)",
			Pattern.CASE_INSENSITIVE);
	
	/***
	 * 验证手机号码
	 * @param str
	 * @return
	 */
	public static boolean str_isPhoneNumber(String str){
		if(str==null||"".equals(str.trim()))
			return false;
		Pattern p = Pattern.compile("1\\d{10}");
		Matcher m = p.matcher(str);   
		boolean b = m.matches();   
		return b; 
	}

	/**
	 * 判断字符串是否为空
	 * @param s
	 * @return
	 */
	public static boolean isNotEmpty(String s){
		if(s==null||s.equals("")){
			return true;
		}
		return false;
	}
	/**
	 * 验证字符串是否为数字
	 * @Title: str_isNumber
	 *@Description: TODO
	 * @param @param str
	 * @param @return
	 *@return boolean
	 * @author 李松茂   
	 * 2015-10-28 下午05:54:44
	 */
	public static boolean str_isNumber(String str){
		if(str==null||"".equals(str.trim()))
			return false;
		Pattern p = Pattern.compile("-?[0-9]+$");
		Matcher m = p.matcher(str);   
		boolean b = m.matches();   
		return b; 
	}
	
	/**
	 * 世界ol礼包领取验证账号
	 * @Title: str_isNumber
	 *@Description: TODO
	 * @param @param str
	 * @param @return
	 *@return boolean
	 * @author 李松茂   
	 * 2015-10-28 下午05:54:44
	 */
	public static boolean str_ganitmecodeaccount(String str){
		if(str==null||"".equals(str.trim()))
			return false;
		Pattern p = Pattern.compile("^[a-zA-z0-9]*\\d{5}$");
		Matcher m = p.matcher(str);   
		boolean b = m.matches();   
		return b; 
	}
	
	public static void main(String [] args){
	}
}
