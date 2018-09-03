package com.wjx.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wjx.entity.Moudle;
import com.wjx.entity.Register;
import com.wjx.service.MoudleService;

@Controller
public class MoudleController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private MoudleService moudleService;
	
	@Autowired
	HttpServletRequest request;
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@RequestMapping("/moudleSuccess")
	public String moudleSuccess(){
		return "addMoudle_success";
	}
	
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
		return "redirect:/moudleSuccess";
	}
	
	@RequestMapping(value="/moudle", method=RequestMethod.GET)
	public String gotoMoudle(@RequestParam("title") String title){
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
		}else{
			return null;
		}
	}
	
	@RequestMapping("/logins")
	public String logins(){
		HttpSession session=request.getSession();
		Register reg=(Register) session.getAttribute("user");
		if(reg!=null){
			return "redirect:/user/main";
		}else{
			return "login";
		}
		
	}
	
}
