package com.pcitc.impl.rtcal.common.constant;

/**
 * 
 * @ClassName: UrlCode 
 * @Description: URL转换码定义，主要用于变量等特殊字符的编码转换 
 * @author liyang
 * @date 2016年10月21日 下午1:48:37 
 * @version 1.0V
 */
public enum UrlCode {

	Space(" ","%20"),
	Percent("%","%25"),
	Caret("^","%5E"),
	Pound("#","%23"),
	Less("<","%3C"),
	Greater(">","%3E"),
	Slash("\\","%5C"),
	
	Point(".","u002e"),
	Dollar("$","u0024"),
	Power("^","u005e"),
	Brace("{","u007b"),
	Rbrace("}","u007d"),
	Bracket("[","u005b"),
	Rbracket("]","u005d"),
	/*Lparenthesis("(","u0028"),*/
	Vertical("|","u007c"),
	/*Rparenthesis(")","u0029"),*/
	Asterisk("*","u002a"),
	Plus("+","u002b"),
	Question("?","u003f"),
	Backslash("\\","u005c"),
	Slashe("/","u002f"),
	Lmarks("\"","u0022"),
	Underlined("_","u005f"),
	Semicolon(";","u003b"),
	Also("&","u0026")
	;
	
	private String resource;
	private String urlcode;
	
	private UrlCode(String resource,String urlcode){
		this.resource = resource;
		this.urlcode = urlcode;
	}

	public String getResource() {
		return resource;
	}

	public String getUrlcode() {
		return urlcode;
	}
	
}
