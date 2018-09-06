package com.wjx.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
	
	private final String MAIN="main";
	
	@Autowired
	private Register register;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MoudleService moudleService;
	
	@Autowired
	HttpServletRequest request;	
	
	
	
	@RequestMapping("/main")
	public String main(Map<String,Object> map){
		List<Moudle> moudleList=moudleService.getMoudleLists();
		HttpSession session=request.getSession();
		String user_id=session.getAttribute("user_id").toString();
		User user=userService.getUserById(user_id);
		map.put("moudles", moudleList);
		map.put("user", user);
		return MAIN;
	}
	
	//�����̰߳�ȫ����
	@RequestMapping("/register")
	public synchronized String register(@RequestParam("phone") String phone, @RequestParam("email") String email, 
			 @RequestParam("password") String password, Map<String,String> map) throws ParseException, InterruptedException{
		
		Register regphone=userService.existPhones(phone);
		Register regemail=userService.existEmails(email);
		if(regphone!=null){
			map.put("msg", "existphone");
			return "redirect:/register.jsp";
		}else if(regemail!=null){
			map.put("msg", "existemail");
			return "redirect:/register.jsp";
		}else{
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
			return "redirect:/success";
		}
		
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam(value="name",required=false) String name, @RequestParam(value="password",required=false) String password, Map<String,String> map){		
		Register reg=userService.findLoginNames(name);
		String username=null, user_id=null;
		if(reg!=null){
			username=reg.getLogin_name();
			user_id=reg.getUser_id();
		}		
		try {			
			Subject subject=SecurityUtils.getSubject();
			UsernamePasswordToken token=new UsernamePasswordToken(username,password);
			subject.login(token);
			HttpSession session=request.getSession();
			session.setAttribute("user_id", user_id);	
			return "redirect:/user/main";
			
		} catch (AuthenticationException e) {
			map.put("msg", "error");
			return "redirect:/logins";
		}

	}
	
	@RequestMapping("/logout")
	public String logout(){
		Subject subject=SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/logins";
	}

	
	
	
	
	
}
