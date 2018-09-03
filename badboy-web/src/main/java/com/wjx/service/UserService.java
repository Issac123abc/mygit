package com.wjx.service;

import com.wjx.entity.Register;
import com.wjx.entity.User;

public interface UserService {
	User getUserById(String id);
	
	void addRegs(Register register);
	
	Register checkRegs(String name);
	
	Register checkLogins(String name, String password);
	
}
