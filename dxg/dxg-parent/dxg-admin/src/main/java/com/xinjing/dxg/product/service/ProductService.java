package com.xinjing.dxg.product.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinjing.dxg.common.BaseService;
import com.xinjing.dxg.common.PageData;
import com.xinjing.dxg.common.PagerParam;
import com.xinjing.dxg.common.utils.UUIDUtil;
import com.xinjing.dxg.product.entity.ProductType;
import com.xinjing.dxg.product.repository.ProductTypeRepository;

@Service
public class ProductService extends BaseService {
	
	@Autowired
	private ProductTypeRepository productTypeRepository;
	
	public PageData<ProductType> getProductType(String userId, PagerParam pageParam) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		sql.append("select id,name name,xh xh,is_total isTotal from t_product_type where user_id =:userId and is_delete=0 order by xh asc ");
		PageData<ProductType> pageData = super.pageBySql(sql.toString(), params, pageParam.getPageSize(), pageParam.getPageNumber(), ProductType.class);
		return pageData;
	}
	
	public void addProductType(String userId, String name, Integer xh, Integer isTotal){
		ProductType entity = new ProductType();
		entity.setId(UUIDUtil.uuid());
		entity.setIsDelete(0);
		entity.setUserId(userId);
		entity.setName(name);
		entity.setXh(xh);
		if(isTotal == null){
			entity.setIsTotal(0);
		}else{
			entity.setIsTotal(1);
		}
		productTypeRepository.save(entity);
	}
	
	public void editProductType(String userId, String id, String name, Integer xh, Integer isTotal){
		ProductType entity = new ProductType();
		entity.setId(id);
		entity.setUserId(userId);
		entity.setName(name);
		entity.setXh(xh);
		if(isTotal != null){
			entity.setIsTotal(isTotal);
		}else{
			entity.setIsTotal(0);
		}
		entity.setIsDelete(0);
		productTypeRepository.save(entity);
	}
	
	@Transactional
	public void removeProductType(String id){
		productTypeRepository.remove(id);
	}
}
