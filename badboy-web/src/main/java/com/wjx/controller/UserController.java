package com.wjx.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjx.entity.Register;
import com.wjx.entity.User;
import com.wjx.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private final String SUCCESS="success";
	private final String MAIN="main";
	
	@Autowired
	private Register register;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/test")
	public String test(){
		String uuid=UUID.randomUUID().toString();
		System.out.println("uuid:"+uuid);
		return SUCCESS;
	}
	
	@RequestMapping("/success")
	public String success(){		
		return SUCCESS;
	}
	
	@RequestMapping("/main")
	public String main(){
		return MAIN;
	}
	
	@RequestMapping("/register")
	public String register(@RequestParam("phone") String phone, @RequestParam("email") String email, 
			 @RequestParam("password") String password) throws ParseException{
		String uuid=UUID.randomUUID().toString();
		String uuid2=UUID.randomUUID().toString();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr=sdf.format(new Date());
		Date date=sdf.parse(dateStr);				
		register.setId(uuid);
		register.setUser_id(uuid2);
		register.setPhone(phone);
		register.setEmail(email);
		//register.setLogin_name(login_name);
		register.setPassword(password);
		register.setRegister_time(date);
		userService.addRegs(register);
		return "redirect:/user/success";
	}
	
	/*@RequestMapping("/login")
	public String login(@RequestParam("name") String name, @RequestParam("password") String password){
		
		return "forward:/user/successlog";
	}*/
	
	@ResponseBody
	@RequestMapping("/existReg")
	public Object existReg(@RequestParam("name") String name, @RequestParam("password") String password){
		Register reg=userService.checkRegs(name);
		Map<String,String> map =new HashMap<String,String>();
		if(null==reg){   //Ã»ÓÐ×¢²á
			map.put("obj", "regError");
			return map;
		}else{      //ÃÜÂë´íÎó
			Register regLogin=userService.checkLogins(name, password);
			if(null==regLogin){
				map.put("obj", "pwError");
				return map;
			}else{		//ÔÊÐíµÇÂ¼		
				return null;
			}
			
		}
					
	}
	
	
	
	
	
}
