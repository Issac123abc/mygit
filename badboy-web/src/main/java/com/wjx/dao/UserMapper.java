package com.wjx.dao;

import com.wjx.entity.Register;
import com.wjx.entity.User;

public interface UserMapper {
	User findById(String id);
	
	void addReg(Register register);
	
	void addUserId(String id);
	
	Register existPhone(String phone);
	
	Register existEmail(String email);
	
	Register checkLogin(String name, String password);
}
