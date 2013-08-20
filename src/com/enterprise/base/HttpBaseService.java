package com.enterprise.base;

import java.io.IOException;
import java.util.List;

import com.enterprise.utils.exception.LTHttpException;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.http.client.RequestParams;
import com.lidroid.xutils.http.client.ResponseStream;

public class HttpBaseService {
		
	/**
	 *     http  请求
	 * @param url
	 * @param postParams
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	protected String post(String url, RequestParams postParams) throws HttpException, IOException{
		HttpUtils http = new HttpUtils();
		ResponseStream stream = null;
		if(postParams==null){
			stream = http.sendSync(HttpMethod.GET, url);
		}else{
				stream = http.sendSync(HttpMethod.POST, url, postParams);
		}
			return stream.readString();
	}
}
