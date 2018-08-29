package com.wjx.dao;

import com.wjx.entity.Register;
import com.wjx.entity.User;

public interface UserMapper {
	User findById(Integer id);
	
	void addReg(Register register);
	
	void addUserId(String id);
	
	Register checkReg(String name);
	
	Register checkLogin(String name, String password);
}
