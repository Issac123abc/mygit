package com.xinjing.dxg.manager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xinjing.dxg.common.ApiResponse;
import com.xinjing.dxg.common.utils.MathUtil;
import com.xinjing.dxg.common.utils.RedisUtil;
import com.xinjing.dxg.common.utils.StringUtil;
import com.xinjing.dxg.common.utils.ValidateUtil;
import com.xinjing.dxg.manager.entity.User;
import com.xinjing.dxg.manager.model.UserModel;
import com.xinjing.dxg.manager.service.UserService;

@RequestMapping("/manager")
@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ApiResponse register(String phone, String code, String password, String email) {
		if (StringUtil.isBlank(phone) || StringUtil.isBlank(code) || StringUtil.isBlank(password)) {
			return ApiResponse.buildRep(1000, null, "参数错误");
		}
		if (!MathUtil.verifySmsCode(phone, code)) {
			return ApiResponse.buildRep(1000, null, "验证码错误");
		}
		try {
			userService.add(phone, password, email);
		} catch (Exception e) {
			return ApiResponse.buildRep(1000, null, e.getMessage());
		}
		return ApiResponse.OK;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ApiResponse<String> login(String phone, String password, String type) {
		if (StringUtil.isBlank(phone) || StringUtil.isBlank(password) || StringUtil.isBlank(type)) {
			return ApiResponse.buildRep(1000, null, "参数错误");
		}
		try {
			String token = userService.login(phone, password, type);
			return ApiResponse.buildRightRep(token, "成功");
		} catch (Exception e) {
			return ApiResponse.buildRep(1000, null, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ApiResponse logout(String token){
		if(StringUtil.isBlank(token)){
			return ApiResponse.buildRep(1000, null, "参数错误");
		}
		userService.logout(token);
		return ApiResponse.OK;
	}
	
	@RequestMapping(value = "/getDetail", method = RequestMethod.GET)
	public ApiResponse<User> getDetail(HttpServletRequest request){
		String token = request.getParameter("token");
		String userId = RedisUtil.get(token);
		User user = userService.getDetail(userId);
		return ApiResponse.buildRightRep(user, "成功");
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ApiResponse edit(UserModel model){
		if(model == null){
			return ApiResponse.buildRep(1000, null, "参数错误");
		}
		if(StringUtil.isBlank(model.getId())){
			return ApiResponse.buildRep(1000, null, "id不能为空");
		}
		try{
			userService.edit(model);
		}catch(Exception e){
			return ApiResponse.buildRep(1000, null, e.getMessage());
		}
		return ApiResponse.OK;
	}
	
	@RequestMapping(value = "/smsCode", method = RequestMethod.GET)
	public ApiResponse<String> smsCode(String phone){
		if(StringUtil.isBlank(phone)){
			return ApiResponse.buildRep(1000, null, "手机号码不能为空");
		}
		if(!ValidateUtil.isMobile(phone)){
			return ApiResponse.buildRep(1000, null, "请输入正确的手机号码");
		}
		String smsCode = MathUtil.smsCode();
		RedisUtil.set(phone, smsCode, 60000*2);
		return ApiResponse.buildRightRep(smsCode, "成功");
	}
	
	@RequestMapping(value = "/passwd", method = RequestMethod.PUT)
	public ApiResponse passwd(String phone, String smsCode, String passwd){
		
		return ApiResponse.OK;
	}
	
	public ApiResponse updateAccount(String phone, String smsCode, String passwd){
		
		return ApiResponse.OK;
	}
}
