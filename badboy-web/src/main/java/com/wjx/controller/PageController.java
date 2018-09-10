package com.wjx.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

	
	@RequestMapping("/success")
	public String success(){
		return "success";
	}
	
	@RequestMapping("/moudleSuccess")
	public String moudleSuccess(){
		return "addMoudle_success";
	}
	
	@RequestMapping("/error")
	public String error(){
		return "error";
	}
		
	@RequestMapping("/logins")
	public String logins(){
		Session session=SecurityUtils.getSubject().getSession();
		Object user_id=session.getAttribute("user_id");
		if(user_id!=null){
			return "redirect:/user/main";
		}else{
			return "login";
		}
		
	}
	
	@RequestMapping("/moudlepage")
	public String moudle(){
		return "moudle";
	}
	
}
