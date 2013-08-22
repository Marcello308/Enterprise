/**
 * FlieName:LTHttpType.java
 * Destribution: http request type and response type 
 * Author:michael
 * 2013-5-17 下午4:01:51
 */
package com.enterprise.utils.http;

/**
 * @author michael
 *
 */
public class LTHttpType {
	
	public enum RequestType {
		MENU,
		HOME_ARTICLE
    }

    public enum ResponseType {
        XML,
        JSON
    }
}
