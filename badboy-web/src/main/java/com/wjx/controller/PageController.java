package com.wjx.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wjx.entity.Register;

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
		/*HttpSession session=request.getSession();
		Register reg=(Register) session.getAttribute("user");
		if(reg!=null){
			return "redirect:/user/main";
		}else{
			return "login";
		}*/
		return "login";
	}
	
}
