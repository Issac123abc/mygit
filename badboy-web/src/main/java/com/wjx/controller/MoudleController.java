package com.wjx.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wjx.entity.Moudle;
import com.wjx.entity.User;
import com.wjx.service.MoudleService;
import com.wjx.service.UserService;

@Controller
public class MoudleController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private MoudleService moudleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	HttpServletRequest request;
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
	
	@RequestMapping(value="/moudle", method=RequestMethod.POST)
	public String addMoudle(@RequestParam("title") String title, @RequestParam("images") MultipartFile file,
			@RequestParam("content") String content) throws IOException, ParseException{
		String path=servletContext.getRealPath("/");
		//保存本地url
		String imageSaveUrl=path+"images\\"+file.getOriginalFilename();
		//保存database url
		String imagedbUrl="images/"+file.getOriginalFilename();		
		OutputStream out=new FileOutputStream(imageSaveUrl);
		out.write(file.getBytes());
		
		String uuid=UUID.randomUUID().toString();
		String regex = "\\s+";
		title = title.replaceAll(regex, "");
		content=content.trim();
		String dateStr=sdf.format(new Date());
		Date date=sdf.parse(dateStr);
		Moudle moudle=new Moudle();
		moudle.setId(uuid);
		moudle.setTitle(title);
		moudle.setImages(imagedbUrl);
		moudle.setContent(content);
		moudle.setCreat_time(date);
		moudleService.addMoudles(moudle);
		return "redirect:/user/main";
	}
	
	@RequestMapping(value="/moudle/{title}", method=RequestMethod.GET)
	public String gotoMoudle(@PathVariable("title") String title,Map<String,Object> map){
		Session session=SecurityUtils.getSubject().getSession();
		String user_id=session.getAttribute("user_id").toString();
		User user=userService.getUserById(user_id);
		map.put("user", user);
		
		if(title.equals("顺风车")){
			return "hitched";
		}
		else if(title.equals("找对象")){
			return "object";
		}
		else if(title.equals("找工作")){
			return "work";
		}
		else if(title.equals("同城打听")){
			return "city";
		}else if(title.equals("会展活动")){
			return "show";
		}else{
			return null;
		}
	}		
	
}
