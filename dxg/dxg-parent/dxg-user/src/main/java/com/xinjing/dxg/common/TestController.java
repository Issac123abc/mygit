package com.xinjing.dxg.common;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

	@RequestMapping("/t1")
	public ApiResponse test(){
		String uuid = UUID.randomUUID().toString();
		String replace = uuid.replace("-", "");
		return ApiResponse.buildRightRep(replace, "成功");
	}
}
