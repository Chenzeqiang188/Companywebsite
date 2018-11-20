package com.good.web.base.utils;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

public class ServletUtil {
	public static void responseToClient(HttpServletResponse resp,Object o,String contentType) throws IOException{
		if(resp == null){
			throw new IllegalArgumentException("resp is null");
		}
		resp.setHeader("content-type", "text/html;charset="+contentType);
		resp.setCharacterEncoding(contentType);
		resp.setContentType(contentType);
		Writer out = resp.getWriter();
		out.write(o.toString());
	}
}
