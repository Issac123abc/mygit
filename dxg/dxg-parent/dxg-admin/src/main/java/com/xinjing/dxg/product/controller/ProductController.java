package com.xinjing.dxg.product.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xinjing.dxg.common.ApiResponse;
import com.xinjing.dxg.common.BaseController;
import com.xinjing.dxg.common.PageData;
import com.xinjing.dxg.common.utils.RedisUtil;
import com.xinjing.dxg.common.utils.StringUtil;
import com.xinjing.dxg.product.entity.Product;
import com.xinjing.dxg.product.entity.ProductType;
import com.xinjing.dxg.product.model.ProductModel;
import com.xinjing.dxg.product.service.ProductService;

@RequestMapping("/product")
@RestController
public class ProductController extends BaseController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/addProductType", method = RequestMethod.POST)
	public ApiResponse addProductType(String name, Integer xh, Integer isTotal, String token){
		if(StringUtil.isBlank(name) || xh == null){
			return ApiResponse.buildRep(1000, null, "参数错误");
		}
		String userId = RedisUtil.get(token);
		productService.addProductType(userId, name, xh, isTotal);
		return ApiResponse.OK;
	}
	
	@RequestMapping(value = "/editProductType", method = RequestMethod.PUT)
	public ApiResponse editProductType(String id, String name, Integer xh, Integer isTotal, String token){
		if(StringUtil.isBlank(id) || StringUtil.isBlank(name) || xh == null){
			return ApiResponse.buildRep(1000, null, "参数错误");
		}
		String userId = RedisUtil.get(token);
		productService.editProductType(userId, id, name, xh, isTotal);
		return ApiResponse.OK;
	}
	
	@RequestMapping(value = "/getProductType", method = RequestMethod.GET)
	public ApiResponse<PageData<ProductType>> getProductType(HttpServletRequest request) {
		String token = request.getParameter("token");
		String userId = RedisUtil.get(token);
		PageData<ProductType> pageData = productService.getProductType(userId, super.getPagerParam(request));
		return ApiResponse.buildRightRep(pageData, "成功");
	}
	
	@RequestMapping(value = "/removeProductType", method = RequestMethod.DELETE)
	public ApiResponse removeProductType(String id) {
		if (StringUtil.isBlank(id)) {
			return ApiResponse.buildRep(1000, null, "参数错误");
		}
		productService.removeProductType(id);
		return ApiResponse.OK;
	}
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ApiResponse addProduct(Product entity, String token) {
		String name = entity.getName();
		Integer status = entity.getStatus();
		String productType = entity.getProductType();
		Double price = entity.getPrice();
		if (StringUtil.isBlank(name) || StringUtil.isBlank(productType) || status == null || price == null) {
			return ApiResponse.buildRep(1000, null, "参数错误");
		}
		String userId = RedisUtil.get(token);
		productService.addProduct(entity, userId);
		return ApiResponse.OK;
	}
	
	@RequestMapping(value = "/editProduct", method = RequestMethod.PUT)
	public ApiResponse editProduct(ProductModel model) {
		String id = model.getId();
		String name = model.getName();
		Integer status = model.getStatus();
		String productType = model.getProductType();
		Double price = model.getPrice();
		if (StringUtil.isBlank(id) || StringUtil.isBlank(name) || StringUtil.isBlank(productType) || status == null || price == null) {
			return ApiResponse.buildRep(1000, null, "参数错误");
		}
		productService.editProduct(model);
		return ApiResponse.OK;
	}
	
	@RequestMapping(value = "/getProduct", method = RequestMethod.GET)
	public ApiResponse<PageData<Product>> getProduct(HttpServletRequest request){
		String token = request.getParameter("token");
		String userId = RedisUtil.get(token);
		PageData<Product> pageData = productService.getProduct(userId, super.getPagerParam(request));
		return ApiResponse.buildRightRep(pageData, "成功");
	}
	
	@RequestMapping(value = "/removeProduct", method = RequestMethod.DELETE)
	public ApiResponse removeProduct(String id){
		if(StringUtil.isBlank(id)){
			return ApiResponse.buildRep(1000, null, "参数错误");
		}
		productService.removeProduct(id);
		return ApiResponse.OK;
	}
	
	@RequestMapping(value = "/updateStatus", method = RequestMethod.PUT)
	public ApiResponse updateStatus(String id, Integer status){
		productService.updateStatus(id, status);
		return ApiResponse.OK;
	}
	
}
