package com.enterprise.utils.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Handler;

/**
 * Created with IntelliJ IDEA.
 * User: michael
 * Date: 12-12-8
 * Time: 下午2:54
 * Description:
 */
public class LTHttpRequestMessage {
	
	private IWebService webService;

    private LTHttpType.RequestType httpType;

    private Map<String, Object> urlParams;

    private Map<String, String> postParams;

    private List<LTHttpParam> urlParamList;

    private List<LTHttpParam> postParamList;

    private Map<String, Object> otherParmas;

    private Handler handler;

    private int handlerMessageID;

    private Map<String, Object> dbRepositories;

    private String showMessage;


    public LTHttpRequestMessage() {

    }
    
    

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public LTHttpRequestMessage(LTHttpType.RequestType httpType, List<LTHttpParam> urlParams,
                         List<LTHttpParam> postParams, Handler handler, int handlerMessageID,
                         IWebService webService) {
        this.httpType = httpType;
        setUrlParamList(urlParams);
        setPostParamList(postParams);
        setHandler(handler);
        setHandlerMessageID(handlerMessageID);
        setWebService(webService);
    }
    
    

    public IWebService getWebService() {
		return webService;
	}



	public void setWebService(IWebService webService) {
		this.webService = webService;
	}



	public List<LTHttpParam> getUrlParamList() {
        return urlParamList;
    }

    public void setUrlParamList(List<LTHttpParam> urlParamList) {
        this.urlParamList = urlParamList;
        this.urlParams = new HashMap<String, Object>();
        if (urlParamList != null)
            for (LTHttpParam param : urlParamList) {
                this.urlParams.put(param.getParamName(), param.getParamValue());
            }
    }

    public List<LTHttpParam> getPostParamList() {
        return postParamList;
    }

    public void setPostParamList(List<LTHttpParam> postParamList) {
        this.postParamList = postParamList;
        this.postParams = new HashMap<String, String>();
        if (postParamList != null){
            for (LTHttpParam param : postParamList) {
                this.postParams.put(param.getParamName(), (String) param.getParamValue());
            }
        }else{
        	this.postParamList = new ArrayList<LTHttpParam>();  
        }
    }

    public LTHttpType.RequestType getHttpType() {
        return httpType;
    }

    public void setHttpType(LTHttpType.RequestType httpType) {
        this.httpType = httpType;
    }

    public Object getUrlParam(String key) {
        if (urlParams == null || urlParams.size() == 0) return null;
        return urlParams.get(key);
    }

    public String getPostParams(String key) {
        if (postParams == null || postParams.size() == 0) return null;
        return postParams.get(key);
    }

    public int getHandlerMessageID() {
        return handlerMessageID;
    }

    public void setHandlerMessageID(int handlerMessageID) {
        this.handlerMessageID = handlerMessageID;
    }

    public Map<String, Object> getDbRepositories() {
        return dbRepositories;
    }

    public void setDbRepositories(Map<String, Object> dbRepositories) {
        this.dbRepositories = dbRepositories;
    }

    public String getShowMessage() {
        return showMessage;
    }

    public void setShowMessage(String showMessage) {
        this.showMessage = showMessage;
    }

    public Object getOtherParmas(String key) {
        if (otherParmas == null) return null;

        return otherParmas.get(key);
    }

    public void setOtherParmas(Map<String, Object> otherParmas) {
        this.otherParmas = otherParmas;
    }
}
