package com.xinjing.dxg.manager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xinjing.dxg.common.ApiResponse;

@RequestMapping("/manager")
@RestController
public class UserController {

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ApiResponse register(String phone, String code, String password, String email){
		
		return null;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ApiResponse login(String name, String password){
		
		return null;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ApiResponse logout(){
		
		return null;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ApiResponse edit(){
		
		return null;
	}
	
	@RequestMapping(value = "/smsCode", method = RequestMethod.GET)
	public ApiResponse smsCode(String phone){
		
		return null;
	}
}
