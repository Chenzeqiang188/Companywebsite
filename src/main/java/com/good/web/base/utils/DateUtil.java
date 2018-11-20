package com.good.web.base.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtil {

	/**
	 * 获得某天所在周的第一天
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	/**
	 * 取得某天所在周的最后一天 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	} 
	
	/**
	 * 取得某天相加(减)後的那一天
	 * @param date
	 * @param num(可正可负)
	 * @return
	 */
	public static Date getAnotherDate(Date date, int num) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, num);
		return c.getTime();
	}

	/**
	 * 取得某天所在月的的第一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);// 日，设为一号
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * 取得某天所在月的的最后一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date, boolean isHead) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);// 日，设为一号
		cal.add(Calendar.MONTH, 1);// 月份加一，得到下个月的一号
		cal.add(Calendar.DATE, -1);// 下一个月减一为本月最后一天
		if(isHead){
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
		}else{
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 0);
		}
		return cal.getTime();
	}
	

	/**
	 * 取得某天是一年中的多少周
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);
		return c.get(Calendar.WEEK_OF_YEAR);
	}
	
	/**  
	* @param date1 需要比较的时间 不能为空(null),需要正确的日期格式 ,如：2009-09-12 
	* @param date2 被比较的时间  为空(null)则为当前时间  
	* @param stype 返回值类型   0为多少天，1为多少个月，2为多少年  
	* @return  
	* 举例：
	* compareDate("2009-09-12", null, 0);//比较天
	* compareDate("2009-09-12", null, 1);//比较月
	* compareDate("2009-09-12", null, 2);//比较年
	*/ 
	public static int compareDate(String startDay, String endDay, int stype) {
		int n = 0;
		String[] u = { "天", "月", "年" };
		String formatStyle = stype == 1 ? "yyyy-MM" : "yyyy-MM-dd";

		endDay = endDay == null ? getCurrentDate("yyyy-MM-dd") : endDay;

		DateFormat df = new SimpleDateFormat(formatStyle);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(startDay));
			c2.setTime(df.parse(endDay));
		} catch (Exception e3) {
			System.out.println("wrong occured");
		}
		// List list = new ArrayList();
		while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
			// list.add(df.format(c1.getTime())); // 这里可以把间隔的日期存到数组中 打印出来
			n++;
			if (stype == 1) {
				c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
			} else {
				c1.add(Calendar.DATE, 1); // 比较天数，日期+1
			}
		}
		n = n - 1;
		if (stype == 2) {
			n = n / 365;
		}
		System.out.println(startDay + " -- " + endDay + " 相差多少" + u[stype]
				+ ":" + n);
		return n;
	}

	public static String getCurrentDate(String format) {
		Calendar day = Calendar.getInstance();
		day.add(Calendar.DATE, 0);
		SimpleDateFormat sdf = new SimpleDateFormat(format);// "yyyy-MM-dd"
		String date = sdf.format(day.getTime());
		return date;
	} 
	
	/***
	 * 根据时间获取该时间所在的月份
	* @author:lisongmao
	* @Description:  
	* @param @param d
	* @param @return    
	* @return String    
	* @throws
	 */
	public static String getdate(Date d){
		int m=d.getMonth()+1;
		String meth="";
		switch (m) {
			case 1:
				meth="Jan";
				break;
			case 2:
				meth="Feb";
				break;
			case 3:
				meth="Mar";
				break;
			case 4:
				meth="Apr";
				break;
			case 5:
				meth="May";
				break;
			case 6:
				meth="Jun";
				break;
			case 7:
				meth="Jul";
				break;
			case 8:
				meth="Aug";
				break;
			case 9:
				meth="Sep";
				break;
			case 10:
				meth="Oct";
				break;
			case 11:
				meth="Nov";
				break;
			default:
				meth="Dec";
				break;
		}
		String date1=(d.getYear()+1900)+"-"+meth+"-"+d.getDate();
		return date1;
	}
	/**
	 * @Title: getYear
	 *@Description: 获取某个时间的年份
	 * @param @param d
	 * @param @return
	 *@return Integer
	 * @author 李松茂   
	 * 2016-7-11 上午10:59:18
	 */
	public static Integer getYear(Date d){
		 Calendar c = Calendar.getInstance();
		 c.setTime(d);
		return c.get(Calendar.YEAR);
	}
	
	
	
	public final static int YEAR_MONTH_DAY = 1;
	public final static int FULL_DATE = 2;	
	public final static int HOUR_MINUTE_SECOND = 3;	
	
	/**
	 * 时间格式化
	 */
	public static String formatDate(Date date,int formatType){
		DateFormat df = null;
		if(formatType==1)
			df = new SimpleDateFormat("yyyy-MM-dd");
		else if(formatType==2)
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		else if(formatType==3)
			df = new SimpleDateFormat("HH:mm:dd");
		return df.format(date);
	}
	
	/**
	 * 字符串转变为Date
	 */
	public static Date stringToDate(String str,int formatType){
		DateFormat df = null;
		Date date = null;
		try {
			if(formatType==1)
				df = new SimpleDateFormat("yyyy-MM-dd");
			else if(formatType==2)
				df = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
			else if(formatType==3)
				df = new SimpleDateFormat("HH:mm:dd");
			date = df.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 今天的0点
	 * @param args
	 */
	public static Date getZero(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	/**
	 * 指定日期的0点
	 * @param args
	 */
	public static Date getZero(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	/**
	 * 今天的0点
	 * @param args
	 */
	public static long getZeroInMillis(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTimeInMillis();
	}
	
	/**
	 * 获得星期，天一二三四五六对就1234567
	 * @param args
	 */
	public static int getDayOfWeek(){
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DAY_OF_WEEK);
	}
	/**
	 * 获得取两个时间之间的日期数组(string)
	 */
	public static List<String> stringArrayBetweenDate(Date start,Date end){
		List<String> dateList = new ArrayList<String>();
		if(end.getTime()<start.getTime()){
			Date temp = start;
			start = end;
			end = temp;
		}
		Date date1 = getZero(start);
		Date date2 = getZero(end);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		while(date1.getTime()<=date2.getTime()){
			dateList.add(df.format(date1));
			date1 = new Date(date1.getTime() + 1000 * 60 *60 * 24);
		}
		return dateList;
	}
	/**
	 * 获得取两个时间之间的日期数组
	 */
	public static List<Date> dateArrayBetweenDate(Date start,Date end){
		List<Date> dateList = new ArrayList<Date>();
		if(end.getTime()<start.getTime()){
			Date temp = start;
			start = end;
			end = temp;
		}
		Date date1 = getZero(start);
		Date date2 = getZero(end);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		while(date1.getTime()<=date2.getTime()){
			dateList.add(date1);
			date1 = new Date(date1.getTime() + 1000 * 60 *60 * 24);
		}
		return dateList;
	}
	
	public static void main(String[] args) {
		for(Date s:dateArrayBetweenDate(new Date(1111111111111l), new Date())){
			System.out.println(s);
		}
	}
}
