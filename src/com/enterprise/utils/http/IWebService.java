/**
 * FlieName:IWebService.java
 * Destribution:
 * Author:michael
 * 2013-5-20 上午10:12:37
 */
package com.enterprise.utils.http;

import com.enterprise.utils.exception.LTDBException;
import com.enterprise.utils.exception.LTHttpException;
import com.enterprise.utils.exception.LTParserException;

/**
 * @author michael
 *
 */
public interface IWebService {
	
	
	
	
	Object httpPost(LTHttpRequestMessage httpMessage) throws LTHttpException, LTParserException, LTDBException;

}
