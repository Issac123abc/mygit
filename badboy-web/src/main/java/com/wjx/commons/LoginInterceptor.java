package com.wjx.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wjx.entity.Register;

public class LoginInterceptor implements HandlerInterceptor{

	private static final String[] IGNORE_URI = {"register","login","success"};
	
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		boolean flag=false;
		String url=request.getRequestURL().toString();
		for(String s:IGNORE_URI){
			if(url.contains(s)){
				flag=true;
			}
		}
		if(!flag){
			HttpSession session=request.getSession();
			Register reg=(Register) session.getAttribute("user");
			if(reg==null){
				session.setAttribute("msg", "nologin");
				response.sendRedirect("/badboy-web/logins");
			}else{
				flag=true;
			}
		}
		
		return flag;
	}

}
