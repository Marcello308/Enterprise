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
		LOGIN,
		GET_HOME_LIST,
        GET_EMAIL_FOLDER,
        GET_EMAIL_LIST,
        GET_EMAIL_DETAIL,
        // 通讯录
        GET_INTERNAL_CONTACT,
        GET_MY_CONTACT,
        GET_INTERNAL_STRUCTURE,
        GET_MY_STRUCTURE,
        GET_FILEMANAGEMENT_TODOS,
       GET_FILEMANAGEMENT_TODOS_DETAIL,
       GET_FILEMANAGEMENT_TODOS_SUBMIT,
        GET_FILEMANAGEMENT_DOENS,
        GET_DOCUMENT,
        GET_DOCUMENT_DETAIL,
        GET_DOCUMENT_ATTACH,
        // 信息
        GET_MARKERTS,
        GET_MARKERTS_DETAIL,
        GET_CATEGORY,
        GET_MESSAGELIST,
        GET_MESSAGE_DETAIL,
        GET_RANKS,
        GET_NEWS,
        GET_NEWS_DETAIL,
        GET_COMMENT_LIST,
        SUBMIT_COMMENT,
        GET_UPLOAD_ATTACH,
        SUBMIT_MESSAGE,
        //日程
        GET_SCHEDULE,
        //论坛
        GET_FORUM_CATEGORY,
        GET_FORUM_POSTLIST,
        GET_FORUM_IMAGEPOST,
        GET_FORUM_POSTDETAIL,
        GET_FORUM_CATEGORY_RANK,
        GET_FORUM_USER_RANK
    }

    public enum ResponseType {
        XML,
        JSON
    }
}
