package com.wjx.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.wjx.entity.Moudle;
import com.wjx.entity.Register;
import com.wjx.entity.User;
import com.wjx.service.MoudleService;
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
	
	@Autowired
	private MoudleService moudleService;
	
	@Autowired
	HttpServletRequest request;
	
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
	public String main(Map<String,Object> map){
		List<Moudle> moudleList=moudleService.getMoudleLists();
		HttpSession session=request.getSession();
		Register reg=(Register) session.getAttribute("user");
		String user_id=reg.getUser_id();
		User user=userService.getUserById(user_id);
		map.put("moudles", moudleList);
		map.put("user", user);
		return MAIN;
	}
	
	//存在线程安全问题
	@RequestMapping("/register")
	public synchronized String register(@RequestParam("phone") String phone, @RequestParam("email") String email, 
			 @RequestParam("password") String password, Map<String,String> map) throws ParseException, InterruptedException{
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
		map.put("success", "register_success");
		return "redirect:/user/success";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam(value="name",required=false) String name, @RequestParam(value="password",required=false) String password, Map<String,String> map){		
		Register exist=userService.checkLogins(name, password);
		if(exist!=null){
			HttpSession session=request.getSession();
			session.setAttribute("user", exist);			
			return "redirect:/user/main";
		}else{
			map.put("msg", "error");
			return "login";
		}	
	}
	
	@RequestMapping("/logout")
	public String logout(){
		HttpSession session=request.getSession();
		session.removeAttribute("user");
		return "redirect:/logins";
	}

	
	
	
	
	
}
