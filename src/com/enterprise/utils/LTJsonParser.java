package com.enterprise.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.enterprise.R;
import com.enterprise.utils.exception.LTParserException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LTJsonParser {

    public static String getStringFromInputStream(InputStream is) throws LTParserException {
        if (is == null) {
            return null;
        }
        BufferedReader br
                = new BufferedReader(
                new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
        } catch (IOException e1) {
            throw new LTParserException("从流进行解析失败！");
        }

        String str = sb.toString();
        return str;

    }


    public static <T> T fromJsonStream(Class<T> cls, InputStream is) throws LTParserException {
        String jsonString = getStringFromInputStream(is);
        return fromJsonString(cls, jsonString);
    }


    public static <T> T fromJsonString(Class<T> cls, String jsonString) throws LTParserException {
    	
    	try {
    		
    		 T entity = null;
    	     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    	     entity = gson.fromJson(jsonString, cls);
    	     return entity;
		} catch (Exception e) {
			throw new LTParserException(String.format("%s(%s:%s)",e.getMessage(),
				"解析的字符串为"
					,jsonString));
		}
       
    }
}
