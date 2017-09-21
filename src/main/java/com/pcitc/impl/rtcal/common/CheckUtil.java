package com.pcitc.impl.rtcal.common;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pcitc.impl.rtcal.exception.BusiException;

/**
 * 各种校验的工具类
 * 
 * @author haiwen.wang  
 *
 */
public class CheckUtil {   

	/**
	 * 
	 * @param field
	 * @return
	 */
	public static boolean checkStringIsNull(String field) {
		if (field == null || field.trim().length() <= 0 ) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param field
	 * @return
	 */
	public static boolean checkCrtMntNameIsNull(String field) {
		if (field == null || field.trim().length() <= 0 || field.trim().length() > 16) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param field
	 * @return
	 */
	public static boolean checkAppCodeIsNull(String field) {
		if (field == null || field.trim().length() <= 0 || field.trim().length() > 25) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param field
	 * @return
	 */
	public static boolean checkNameIsNull(String field) {
		if (field == null || field.trim().length() <= 0 || field.trim().length() > 64) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param field
	 * @return
	 */
	public static boolean checkStringIsNotNull(String field) {
		if (field != null && field.trim().length() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param value
	 * @return
	 * @throws BusiException 
	 */
	public static boolean checkDigit(String value) throws BusiException {
		try {
			int value1 = Integer.parseInt(value);
			if(value1<0){
				return false;
			}
		} catch (Exception e) {
			throw new BusiException(ErrorCodeEnum.M001, e.getMessage(), e);
		}
		return true;
	}
	
	/**
	 * 判断是否是大于1的整数
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){  
	    Pattern pattern = Pattern.compile("[0-9]*");  
	    return pattern.matcher(str).matches();     
	}  

	/**
	 * 
	 * @param timeStamp
	 * @return
	 */
	public static boolean checkTimeStamp(String timeStamp) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			format.setLenient(false);
			format.parse(timeStamp);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param ip
	 * @return
	 */
	public static boolean checkIP(String addr) {
		if (addr.length() < 7 || addr.length() > 15 || "".equals(addr)) {
			return false;
		}		
		//判断IP格式和范围
		String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		Pattern pat = Pattern.compile(rexp);
		Matcher mat = pat.matcher(addr);
		return mat.find();
	}
	
	/**
	 * @Title: filter 
	 * @Description: 特殊字符判断
	 */
	public static boolean characterFilter(String character) {
		String rexp = "[^(A-Za-z0-9_\\u4e00-\\u9fa5)]";
		Pattern pat = Pattern.compile(rexp);
		Matcher mat = pat.matcher(character);
		return mat.find();
	}
		
	 /**
	  * @Title: checkMatcher 
	  * @Description: 数字字母下划线验证
	  */
	 public static Matcher checkMatcher(String field){
		 Matcher matcher  = Pattern.compile("^[0-9a-zA-Z_]+$").matcher(field);
		 return matcher;
	 }

	/**
	 * starttime必须小于endtime
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static boolean compareTime(String startTime, String endTime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		format.setLenient(false);
		try {
			Date startDate = format.parse(startTime);
			Date endDate = format.parse(endTime);
			if(startDate.before(endDate)){
				return true;
			}else{
				return false;
			}
		} catch (ParseException e) {
			return false;
		}
	}
	
	 /**
	  * @Title: checkMatcher 
	  * @Description: 数字字母下划线验证
	  */
	 public static Matcher checkMatcherName(String field){
		 Matcher matcher  = Pattern.compile("^[a-zA-Z\\u4e00-\\u9fa5]+$").matcher(field);
		 return matcher;
	 }
	 
}
