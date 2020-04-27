package com.xinjing.dxg.manager.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xinjing.dxg.common.ApiResponse;

@RequestMapping("/upload")
@RestController
public class UploadController {

	@RequestMapping(method = RequestMethod.POST)
	public ApiResponse upload(MultipartFile[] files) {
		if (files == null) {
			return ApiResponse.buildRep(1000, null, "参数错误");
		}
		try {
			for (MultipartFile m : files) {
				String originalFilename = m.getOriginalFilename();
				File file = new File("../../images/" + originalFilename);
				OutputStream os = new FileOutputStream(file);
				os.write(m.getBytes());
				os.close();
			}
		} catch (Exception e) {
			return ApiResponse.buildRep(1000, null, e.getMessage());
		}
		return ApiResponse.OK;
	}
	
}
