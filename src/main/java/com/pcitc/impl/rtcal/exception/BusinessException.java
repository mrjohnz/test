package com.pcitc.impl.rtcal.exception;

/**
 * BusinessException 业务异常
 * @author pcitc
 * @version 2.0
 */
public class BusinessException extends Exception {
	/**  
	 * @Fields serialVersionUID
	 */          
	private static final long serialVersionUID = 1L;
	
	public BusinessException(String title, String code, String message){
		
		this.title = title;
		this.code = code;
		this.message = message;
	}

	private String title;
	
	private String code;
	
	private String message;

	public String getTitle() {
		return title;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
}
