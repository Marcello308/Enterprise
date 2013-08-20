/**
 * FlieName:LTParserException.java
 * Destribution:
 * Author:michael
 * 2013-5-20 上午10:49:25
 */
package com.enterprise.utils.exception;

import com.enterprise.utils.LTToolUtil;

/**
 * @author michael
 *
 */
public class LTParserException extends Exception{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1219595618743635633L;


	public LTParserException(String parserMessage) {
		super(parserMessage);
	}


	@Override
	public String getMessage() {
		if(LTToolUtil.isNull(super.getMessage()))
			return "解析异常：未知错误！";
		else
			return String.format("解析异常：%s", super.getMessage());
	}

}
