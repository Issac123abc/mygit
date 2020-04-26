package com.xinjing.dxg.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtils {
	
	public static String getDateStr(Date date, String pattern){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			String dateStr = sdf.format(date);
			return dateStr;
		}catch(Exception e){
			return null;
		}		
	}
	
	public static Date getDate(String date, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date time = null;
		try {
			time = sdf.parse(date);
			return time;
		} catch (ParseException e) {
			return time;
		}
	}
	
	public static String dateStr(String date, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			Date time = sdf.parse(date);
			String dateStr = sdf.format(time);
			return dateStr;
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 获取最近多少天的开始与结束时间
	 * 
	 * */
	public static Map<String, Date> getRecentDates(Date endTime, int value){
		Map<String, Date> dates = new HashMap<String, Date>();
		dates.put("endTime", endTime);
		Calendar c = Calendar.getInstance();
		c.setTime(endTime);
		c.add(Calendar.DATE, value);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		Date startTime = c.getTime();
		dates.put("startTime", startTime);
		return dates;
	}
	

	/**
	 * 24小时字符串数组
	 * 
	 * */
	public static String[] getFullHourStr(){
		String[] hourArr = new String[24];	    
		for (int i = 0; i < hourArr.length; i++) { 
			StringBuilder str = new StringBuilder();
			if (i < 10) {
				hourArr[i] = str.append("0").append(String.valueOf(i)).append(":00").toString();
			} else {
				hourArr[i] = str.append(String.valueOf(i)).append(":00").toString();
			}
		}
		return hourArr;
	}
	
	/**
	 * 月份字符串数组
	 * 
	 * */
	public static String[] getMonthStr() {
		String[] monthArr = new String[12];
		for (int i = 0; i < monthArr.length; i++) { 
			monthArr[i] = String.valueOf(i + 1);
		}
		return monthArr;
	}
	
}
