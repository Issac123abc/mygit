package com.xinjing.dxg.manager.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xinjing.dxg.common.ApiResponse;
import com.xinjing.dxg.common.utils.UUIDUtil;

@RequestMapping("/upload")
@RestController
public class UploadController {
	
	@RequestMapping(method = RequestMethod.POST)
	public ApiResponse<String> upload(MultipartFile[] files) throws IOException {
		if (files == null) {
			return ApiResponse.buildRep(1000, null, "参数错误");
		}
		OutputStream out = null;
		try {
			for (MultipartFile file : files) {
				String originalFilename = file.getOriginalFilename();
				if (originalFilename == null) {
					continue;
				}
				String[] split = originalFilename.split("\\.");
				if (split.length < 2) {
					continue;
				}
				String fileName = UUIDUtil.uuid() + "." + split[1];
				String path = "d:"+File.separator+"dxg"+File.separator+"images"+File.separator + fileName;
				File newFile = new File(path);
				newFile.createNewFile();
				out = new FileOutputStream(newFile);
				out.write(file.getBytes());
			}
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			out.close();
		}
		return ApiResponse.buildRightRep(null, "成功");
	}
	
}
