package com.good.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

public interface InputService {

	String switchHeadle(int cmdId, JSONObject data, HttpServletRequest request, HttpServletResponse response);

}
