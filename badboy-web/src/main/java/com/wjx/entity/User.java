package com.wjx.entity;

import org.springframework.stereotype.Component;

@Component
public class User {
	private String id;
	private String username;
	private Integer age;
	private Integer gender;
	private String photo;
	private String region;
	private String autograph;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getAutograph() {
		return autograph;
	}
	public void setAutograph(String autograph) {
		this.autograph = autograph;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public User() {
		
	}
		
	
	public User(String id, String username, Integer age, Integer gender,
			String photo, String region, String autograph) {
		super();
		this.id = id;
		this.username = username;
		this.age = age;
		this.gender = gender;
		this.photo = photo;
		this.region = region;
		this.autograph = autograph;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", age=" + age
				+ ", gender=" + gender + ", photo=" + photo + ", region="
				+ region + ", autograph=" + autograph + "]";
	}
	
	
	
	
	
	
	
}
