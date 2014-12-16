package com.isbn.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {

	public static void print(HttpServletResponse response , Object obj){
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().println(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
