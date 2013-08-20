package com.enterprise.utils.http;

import com.enterprise.R;
import com.enterprise.utils.LTJsonParser;
import com.enterprise.utils.LTToolUtil;
import com.enterprise.utils.exception.LTParserException;


/**
 * Created with IntelliJ IDEA.
 * User: michael
 * Date: 12-12-8
 * Time: 下午4:37
 * Description:
 */
public class LTParserResponse {

    public static <T> T parserResponse(LTHttpType.ResponseType responseType,
                                                                   String response, Class<T> cls) throws LTParserException {
        if (responseType == LTHttpType.ResponseType.JSON) {
            return LTJsonParser.fromJsonString(cls, response);
        }
        throw new LTParserException(LTToolUtil.getResourceString(R.string.config_response_error_not_json));
    }
}
