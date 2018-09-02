package com.wjx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wjx.entity.Moudle;
import com.wjx.entity.User;
import com.wjx.service.UserService;

@Controller
public class TestController {
	
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/hello")
	public String hello(){
		System.out.println("test...");
		return "success";
	}
	
	@RequestMapping("/getUser")
	public String getUser(){
		User user=userService.getUserById(1);
		System.out.println("user:"+user);
		return "success";
	}
	
}
