package org.front.end.wits.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ch.qos.logback.classic.BasicConfigurator;

public class LoggerInterceptor extends HandlerInterceptorAdapter {

	static Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

	static{
		BasicConfigurator.configureDefaultContext();
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.info("Before handling the request");
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("After handling the request");
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("After rendering the view");
		super.afterCompletion(request, response, handler, ex);
	}
}
