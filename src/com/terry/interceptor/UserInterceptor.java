package com.terry.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String userId = request.getHeader("userId");
		if("abc".equals(userId)) {
			request.setAttribute("userId",userId);
			return true;
		}
		else {
			response.setStatus(403);
			return false;
		}
	}

	
}
