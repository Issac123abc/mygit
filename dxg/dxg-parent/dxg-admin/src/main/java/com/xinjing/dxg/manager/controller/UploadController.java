package com.xinjing.dxg.manager.controller;

import java.io.IOException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.luhuiguo.fastdfs.domain.StorePath;
import com.luhuiguo.fastdfs.service.FastFileStorageClient;
import com.xinjing.dxg.common.ApiResponse;

@RequestMapping("/upload")
@RestController
public class UploadController {
	
	@Autowired
	private FastFileStorageClient storageClient;

	@RequestMapping(method = RequestMethod.POST)
	public ApiResponse<String> upload(MultipartFile file) {
		if (file == null) {
			return ApiResponse.buildRep(1000, null, "参数错误");
		}
		// FilenameUtils.getExtension(""):取到一个文件的后缀名
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		// group1:指storage服务器的组名
		StorePath uploadFile;
		try {
			uploadFile = storageClient.uploadFile("group1", file.getInputStream(), file.getSize(), extension);
		} catch (IOException e) {
			return ApiResponse.buildRep(1000, null, e.getMessage());
		}
		// 返回它在storage容器的的路径
		String path = uploadFile.getFullPath();
		return ApiResponse.buildRightRep(path, "成功");
	}
	
}
