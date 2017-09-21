package com.pcitc.impl.rtcal.exception;

import com.pcitc.impl.rtcal.common.ErrorCodeEnum;

/**
 * 异常
 * @author pcitc
 *
 */
public class BusiException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private ErrorCodeEnum code;//错误码
	
	public BusiException(ErrorCodeEnum code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}
  
    public BusiException(ErrorCodeEnum code, String message) {
		super(message);
		this.code = code;
	}

	public ErrorCodeEnum getCode() {
		return code;
	}



}
