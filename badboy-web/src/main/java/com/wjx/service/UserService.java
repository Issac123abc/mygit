package com.wjx.service;

import com.wjx.entity.Register;
import com.wjx.entity.User;

public interface UserService {
	User getUserById(String id);
	
	Register findLoginNames(String name);
	
	void addRegs(Register register);
	
	Register existPhones(String phone);
	
	Register existEmails(String email);
	
	Register checkLogins(String name, String password);
	
	void UpdateUsers(User user);
}
