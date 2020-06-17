package com.xinjing.dxg.order.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xinjing.dxg.common.ApiResponse;

@RequestMapping("/order")
@RestController
public class OrderController {

	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	public ApiResponse addOrder(){
		
		return ApiResponse.OK;
	}
	
	@RequestMapping(value = "/delOrder", method = RequestMethod.DELETE)
	public ApiResponse delOrder(String id){
		
		return ApiResponse.OK;
	}
	
}


