package com.enterprise.utils.http;




/**
 * Created with IntelliJ IDEA.
 * User: michael
 * Date: 12-12-8
 * Time: 下午4:21
 * Description:
 */
public class LTHttpUrl {

/*    public static String getUrl(LTHttpType.RequestType requestType, List<LTHttpParam> urlParams) {
        String url = "";
        switch (requestType) {
          
            case GET_FORUM_USER_RANK:	
            	url = String.format("%s%s", LTConstants.API_DEFAULT_HOST, LTConstants.API_GET_FORUM_USER_RANK);
            	break;
            	
            	
            default:
        }
        if (urlParams == null) {
            return url;
        } else {
            if (urlParams.size() == 0) return null;
            for (LTHttpParam httpParam : urlParams) {
                String key = String.format("${%s}", httpParam.getParamName());
                String value = httpParam.getParamValue();
                url = url.replace(key, value);
            }
            return url.trim();
        }
    }*/
}
