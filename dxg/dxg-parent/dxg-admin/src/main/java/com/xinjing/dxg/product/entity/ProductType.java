package com.xinjing.dxg.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_product_type")
public class ProductType {

	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "xh")
	private Integer xh;
	
	@Column(name = "is_total")
	private Integer isTotal;
	
	@Column(name = "is_delete")
	private Integer isDelete;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getXh() {
		return xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}

	public Integer getIsTotal() {
		return isTotal;
	}

	public void setIsTotal(Integer isTotal) {
		this.isTotal = isTotal;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
}
