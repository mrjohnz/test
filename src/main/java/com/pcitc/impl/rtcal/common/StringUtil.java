package com.pcitc.impl.rtcal.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.StringUtils;
import com.pcitc.impl.rtcal.common.constant.UrlCode;



/**
 * StringUtil 
 * @author pcitc
 * @version 1.0
 */
public class StringUtil {
	
	//字母、数字、下划线、中文
	public static final String rexpString = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$";
	//字母、数字、下划线、.、-
	public static final String rexpStringExceptCHS = "^(?!_)(?!.*?_$)[.\\\\a-zA-Z0-9_\\-]+$";
	//IP格式
	public static final String rexpIP = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
	
	/**
	 * 包含字母、数字、下划线
	 */
	public static final String rexpPreFix = "^[0-9a-zA-Z_]+$";

	/**
	 * 校验跳转页
	 */
	public static final String rexpSkip = "[\\d]+";
	
	/**
	 * 校验top
	 */
	public static final String rexpTop = "^[1-9]\\d*$";
	
	/**
	 * 
	 * @Title: validateFormat 
	 * @Description: 验证字符串是否满足字母、数字、下划线、中文的值
	 * @param resource
	 * @return
	 */
	public static boolean validateFormat(String resource){
		return resource.matches(rexpString);
	}
	
	/**
	 * @Title: validatePreFix 
	 * @Description: 校验添加数据源前缀是否合法
	 * @param resource
	 * @return    boolean
	 */
	public static boolean validatePreFix(String resource){
		return resource.matches(rexpPreFix);
	}
	
	/**
	 * @Title: validateSkip 
	 * @Description: 校验跳转页只能输入正整数
	 * @param resource
	 * @return    设定文件
	 */
	public static boolean validateTab(String resource){
		Pattern pat = Pattern.compile(rexpSkip);
		Matcher mat = pat.matcher(resource);
		boolean skip = mat.matches();
		return skip;
	}

	/**
	 * @Title: validateTop 
	 * @Description: 校验top
	 * @param resource
	 * @return    设定文件
	 */
	public static boolean validateTop(String resource){
		return resource.matches(rexpTop);
	}
	
	/**
	 * 
	 * @Title: validateFormatExceptCHS 
	 * @Description: 验证字符串是否满足 字母、数据、下划线、.、-
	 * @param resource
	 * @return   
	 */
	public static boolean validateFormatExceptCHS(String resource){
		return resource.matches(rexpStringExceptCHS);
	}
	
	/**
	 * 
	 * @Title: validateFormatIP 
	 * @Description: 验证IP地址格式
	 * @param resource IP地址
	 * @return    
	 */
	public static boolean validateFormatIP(String resource){
		return  resource.matches(rexpIP);
	}

	/**
	 * 
	 * @Title: convertLikeTOEqual 
	 * @Description: 将SQL中的like转换成= 
	 * @param sql
	 * @return  
	 */
	public static String convertLikeTOEqual(String sql){
		sql = sql.replace("escape '\\'", "");
		return sql.replace("like", "=");
	}
	
	/**
	 * @Title: replaces 
	 * @Description: 处理SQL语句中有特殊字符查询
	 * @param before
	 * @return    设定文件
	 */
	public static String replaces(String before){
		if (before == null) {
			return null;
		}else{
			before = before.trim();
			if (before.indexOf("%") >= 0) {
				return before.replace("%", "\\%");
			}
			if (before.indexOf("_") >= 0) {
				return before.replace("_", "\\_");
			}
			return before;
		}
	}
	
	/**
	 * @Title: trim 
	 * @Description: 去除Stirng中的前后空格
	 * @param before
	 * @return
	 */
	public static String trim(String before){
		return before == null ? null : before.trim();
	}
	
	public static void beanAttributeValueTrim(String... str ) throws Exception {
    	for(String s : str){
    		s = trim(s);
    	}
    }
	
	public static String replaceBar(String resource){
		if (resource == null) {
			return null;
		}else{
			resource = resource.trim();
			if (resource.indexOf("\\%") >= 0) {
				return resource.replace("\\", "");
			}
			return resource;
		}
	}
	
	/**
	 * 
	 * @Title: toUrlEncode 
	 * @Description: 将Stirng类型中的字符转换成URL编码，具体字符参照UrlCode定义
	 * @param resource
	 * @return  
	 */
	public static String toUrlEncode(String resource){
		
		String strUrlcode = resource;
		if (!StringUtils.isEmpty(resource)) {
			for(UrlCode urlcode : UrlCode.values()){
				strUrlcode = strUrlcode.replace(urlcode.getResource(), urlcode.getUrlcode());
			}
		}
		
		return strUrlcode;
	}
	
	/**
	 * 
	 * @Title: toUrlDecode 
	 * @Description: 将Stirng类型中的URL编码转换回特殊字符，具体字符参照UrlCode定义
	 * @param resource
	 * @return   
	 */
	public static String toUrlDecode(String resource){
		
		String strDecode = resource;
		
		if (!StringUtils.isEmpty(resource)) {
			for(UrlCode urlCode : UrlCode.values()){
				strDecode = strDecode.replace(urlCode.getUrlcode(), urlCode.getResource());
			}
		}
		return strDecode;
	}
	
	public static String toNullString(String resource){
		return resource == null ? "" : resource;
	}
}
