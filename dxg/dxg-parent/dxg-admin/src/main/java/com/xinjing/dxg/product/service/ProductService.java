package com.xinjing.dxg.product.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinjing.dxg.common.BaseService;
import com.xinjing.dxg.common.PageData;
import com.xinjing.dxg.common.PagerParam;
import com.xinjing.dxg.common.utils.UUIDUtil;
import com.xinjing.dxg.product.entity.Product;
import com.xinjing.dxg.product.entity.ProductType;
import com.xinjing.dxg.product.model.ProductModel;
import com.xinjing.dxg.product.repository.ProductRepository;
import com.xinjing.dxg.product.repository.ProductTypeRepository;

@Service
public class ProductService extends BaseService {
	
	@Autowired
	private ProductTypeRepository productTypeRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
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
	
	public void addProduct(Product entity, String userId) {
		entity.setId(UUIDUtil.uuid());
		entity.setIsDelete(0);
		entity.setUserId(userId);
		Date now = new Date();
		entity.setCreateTime(now);
		entity.setUpdateTime(now);
		productRepository.save(entity);
	}
	
	public void editProduct(ProductModel model) {
		Optional<Product> findById = productRepository.findById(model.getId());
		if (!findById.isPresent()) {
			throw new RuntimeException("产品不存在");
		}
		Product product = findById.get();
		BeanUtils.copyProperties(model, product);
		product.setUpdateTime(new Date());
		productRepository.save(product);
	}
	
	public PageData<Product> getProduct(String userId, PagerParam pageParam){
		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		sql.append("select p.id,p.name,p.price,p.amount,p.status,p.create_time createTime,p.update_time updateTime,pt.name productType from t_product p ");
		sql.append("left join t_product_type pt on pt.id=p.product_type ");
		sql.append("where p.user_id =:userId and p.is_delete=0 order by update_time desc ");
		PageData<Product> pageData = super.pageBySql(sql.toString(), params, pageParam.getPageSize(), pageParam.getPageNumber(), Product.class);
		return pageData;
	}
	
	@Transactional
	public void removeProduct(String id){
		productRepository.remove(id);
	}
	
	@Transactional
	public void updateStatus(String id, Integer status){
		productRepository.updateStatus(id, status);
	}
	
}
