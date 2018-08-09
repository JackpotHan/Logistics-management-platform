package com.xiaohan.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class RequestWrapper extends HttpServletRequestWrapper {
	private byte[] body;
	private Map<String , String[]> params = new HashMap<>();

	public RequestWrapper(HttpServletRequest request) {
		super(request);
		this.params.putAll(request.getParameterMap());
	}

	public RequestWrapper(HttpServletRequest request , JSONObject extendParams) {
        this(request);
		body = JSON.toJSONString(extendParams).getBytes(Charset.forName("UTF-8"));
		addAllParameters(extendParams);//这里将扩展参数写入参数表
    }

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {

		final ByteArrayInputStream bais = new ByteArrayInputStream(body);

		return new ServletInputStream() {
			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public void setReadListener(ReadListener listener) {

			}

			@Override
			public int read() throws IOException {
				return bais.read();
			}
		};
	}

	@Override
    public String getParameter(String name) {//重写getParameter，代表参数从当前类中的map获取
        String[]values = params.get(name);
        if(values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

	@Override
    public String[] getParameterValues(String name) {//同上
         return params.get(name);
    }

	@Override
	public Map<String, String[]> getParameterMap() {
		return params;
	}

	private void addAllParameters(Map<String, Object> extendParams) {
		for(Map.Entry<String , Object> entry : extendParams.entrySet()) {
			params.put(entry.getKey() , new String[] {entry.getValue() + ""});
        }
	}
	
}
