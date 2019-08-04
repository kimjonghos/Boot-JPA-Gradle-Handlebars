package com.example.webservice.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HttpSession session=request.getSession();
		if(session.getAttribute("kakaoid")!=null) {
			if(modelAndView!=null) {
				modelAndView.addObject("kakaoid", session.getAttribute("kakaoid"));
				modelAndView.addObject("profile_image", session.getAttribute("profile_image"));
				modelAndView.addObject("profile_nickname",session.getAttribute("profile_nickname"));
				System.out.println("This is Interceptor accessToken" + session.getAttribute("accessToken"));
			}
		}
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
