package com.wjx.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Register {
	private String id;
	private String user_id;
	private String phone;
	private String email;
	private String login_name;
	private String password;
	private Date register_time;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRegister_time() {
		return register_time;
	}
	public void setRegister_time(Date register_time) {
		this.register_time = register_time;
	}
	
	@Override
	public String toString() {
		return "Register [id=" + id + ", user_id=" + user_id + ", phone="
				+ phone + ", email=" + email + ", login_name=" + login_name
				+ ", password=" + password + ", register_time=" + register_time
				+ "]";
	}
	
	
}
