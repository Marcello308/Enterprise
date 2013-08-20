/**
 * FlieName:LTToolUtils.java
 * Destribution:
 * Author:michael
 * 2013-5-17 下午4:04:18
 */
package com.enterprise.utils;

import java.util.Calendar;

import com.enterprise.base.BaseApplication;

import android.content.SharedPreferences;
import android.util.Log;

/**
 * @author michael
 *
 */
public class LTToolUtil {
	
	
	/**
	 * Destribution：
	 * 		判断字符串是否为空或者是空字符串
	 * @param 
	 * 		传入的字符串
	 * @return
	 * 		返回布尔值
	 */
	public static boolean isNull(String s){

		if(s == null || s.equalsIgnoreCase(""))
			return true;
		return false;
	}
	
	/**
	 * Destribution：
	 * 		打印LOG信息
	 * @param tag
	 * 		TAG信息
	 * @param msg
	 * 		Debug Info
	 */
	public static void DebugInfo(String tag,String msg){
		Log.d(tag, msg);
	}
	
	
	/**
	 * Destribution：
	 * 		获取资源中的字符串
	 * @param resId
	 * 		资源id
	 * @return
	 * 		返回字符串
	 */
	public static String getResourceString(int resId){
		return BaseApplication.getInstance().getString(resId);
	}
	
	/**
	 * Destribution：从SharePreferences中获取值
	 * @param key
	 * 		键值
	 * @return
	 * 		返回保存对应键值的字符串
	 */
	/*
	public static String getStringFromSP(String key){
		SharedPreferences sp = LTOAApplication.getInstance().getSharedPreferences(LTConstants.SETTING_INFOS, 0);
		return sp.getString(key, "");
	}
	
	*//**
	 * Destribution：从SharePreferences中获取值
	 * @param key
	 * 		键值
	 * @return
	 * 		返回对应键值的布尔值
	 *//*
	public static boolean getBooleanFromSP(String key){
		SharedPreferences sp = LTOAApplication.getInstance().getSharedPreferences(LTConstants.SETTING_INFOS, 0);
		return sp.getBoolean(key,false);
	}
	
	public static void writeStringToSP(String key,String val){
		
		
	}
	
	public static void writeBooleanToSP(String key,boolean val){
		SharedPreferences sp = LTOAApplication.getInstance().getSharedPreferences(
                LTConstants.SETTING_INFOS, 0);
        sp.edit().putBoolean(key, val)
                .commit();
	}
	
	public static void saveRemember(String userName,String password,boolean isRemember){
		SharedPreferences sp = LTOAApplication.getInstance().getSharedPreferences(
                LTConstants.SETTING_INFOS, 0);
		if (isRemember) {
			sp.edit().putBoolean(LTConstants.SETTING_ISREMEMBER,isRemember)
	        .putString(LTConstants.SETTING_USERNAME,userName)
	        .putString(LTConstants.SETTING_PASSWORD, password)
	                .commit();
		}else{
			sp.edit().putBoolean(LTConstants.SETTING_ISREMEMBER,isRemember)
	        .putString(LTConstants.SETTING_USERNAME,"")
	        .putString(LTConstants.SETTING_PASSWORD, "")
	                .commit();
			
		}
        
	}*/
	
	
    public  static String getTime(){
   	 Calendar ca = Calendar.getInstance();
       int year = ca.get(Calendar.YEAR);//获取年份
       int month=ca.get(Calendar.MONTH);//获取月份
       int day=ca.get(Calendar.DATE);//获取日
       int minute=ca.get(Calendar.MINUTE);//分
       int hour=ca.get(Calendar.HOUR);//小时
       int second=ca.get(Calendar.SECOND);//秒
       return String.format("%d%d%d%d%d%d", year,month,day,minute,hour,second);
   }
	
	

}
