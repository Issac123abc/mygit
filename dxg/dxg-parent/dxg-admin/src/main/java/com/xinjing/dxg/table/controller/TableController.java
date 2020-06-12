package com.xinjing.dxg.table.controller;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.xinjing.dxg.common.ApiResponse;
import com.xinjing.dxg.common.utils.QrCodeUtil;

@RequestMapping("/table")
@RestController
public class TableController {
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponse add(){
		
		return ApiResponse.OK;
	}
	
	@RequestMapping(value = "/getPageList", method = RequestMethod.GET)
	public ApiResponse getPageList(){
		
		return null;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public ApiResponse edit(){
		
		return ApiResponse.OK;
	}
	
	@RequestMapping(value = "/del", method = RequestMethod.DELETE)
	public ApiResponse del(String id){
		
		return ApiResponse.OK;
	}
	
	@RequestMapping(value = "/getQrCode", method = RequestMethod.GET)
	public void getQrCode(HttpServletResponse response) throws IOException {
		// 二维码内的信息
		String info = "This is my first QR Code";
		byte[] qrcode = null;
		try {
			qrcode = QrCodeUtil.getQRCodeImage(info, 360, 360);
		} catch (WriterException e) {
			System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
		}
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(qrcode);
		outputStream.flush();
		outputStream.close();
	}
	
	@RequestMapping(value = "/getProductFromQrCode", method = RequestMethod.GET)
	public ApiResponse getProductFromQrCode(){
		
		return null;
	}
}
