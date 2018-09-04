package com.wjx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjx.dao.UserMapper;
import com.wjx.entity.Register;
import com.wjx.entity.User;
import com.wjx.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	public User getUserById(String id) {
		return userMapper.findById(id);
	}

	public void addRegs(Register register) {
		userMapper.addReg(register);
		userMapper.addUserId(register.getUser_id());
	}

	public Register existPhones(String phone) {
		return userMapper.existPhone(phone);
	}
	
	public Register existEmails(String email) {
		return userMapper.existEmail(email);
	}

	public Register checkLogins(String name, String password) {
		return userMapper.checkLogin(name, password);
	}

	

}
