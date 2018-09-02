package com.wjx.entity;

import java.util.Date;

public class Moudle {
	private String id;
	private String title;
	private String images;
	private String content;
	private Date creat_time;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreat_time() {
		return creat_time;
	}
	public void setCreat_time(Date creat_time) {
		this.creat_time = creat_time;
	}
	
	@Override
	public String toString() {
		return "Moudle [id=" + id + ", title=" + title + ", images=" + images
				+ ", content=" + content + ", creat_time=" + creat_time + "]";
	}
	
	
}
