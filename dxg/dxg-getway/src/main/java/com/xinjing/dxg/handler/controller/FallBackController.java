package com.xinjing.dxg.handler.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {
	
	@RequestMapping("/fallback")
	public Object communityFallback(){
		return "服务出错了";
	}

}
