package com.wjx.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

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
		HttpSession session=request.getSession();
		System.out.println("access_token:"+session.getAttribute("access_token"));
		List<Moudle> moudleList=moudleService.getMoudleLists();
		map.put("moudles", moudleList);
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
	public String login(@RequestParam("name") String name, @RequestParam("password") String password, Map<String,String> map,
			HttpServletResponse response){		
		Register exist=userService.checkLogins(name, password);
		if(exist!=null){
			String uuid=UUID.randomUUID().toString();
			HttpSession session=request.getSession();
			session.setAttribute("access_token", uuid);
			Cookie cookie=new Cookie("access_token", uuid);
			response.addCookie(cookie);
			System.out.println("sessionId:"+session.getId());
			System.out.println("uuid:"+uuid);
			return "redirect:/user/main";
		}else{
			map.put("msg", "error");
			return "forward:/login.jsp";
		}
		
		
	}
	
	@ResponseBody
	@RequestMapping("/existReg")
	public Object existReg(@RequestParam("name") String name, @RequestParam("password") String password){
		Register reg=userService.checkRegs(name);
		Map<String,String> map =new HashMap<String,String>();
		if(null==reg){   //没有注册
			map.put("obj", "regError");
			return map;
		}else{      //密码错误
			Register regLogin=userService.checkLogins(name, password);
			if(null==regLogin){
				map.put("obj", "pwError");
				return map;
			}else{		//允许登录		
				return null;
			}
			
		}
					
	}
	
	
	
	
	
}
