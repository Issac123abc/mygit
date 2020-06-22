package com.xinjing.dxg.table.controller;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.xinjing.dxg.common.ApiResponse;
import com.xinjing.dxg.common.utils.QrCodeUtil;
import com.xinjing.dxg.common.utils.RedisUtil;
import com.xinjing.dxg.common.utils.StringUtil;
import com.xinjing.dxg.table.service.TableService;

@RequestMapping("/table")
@RestController
public class TableController {
	
	@Autowired
	private TableService tableService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponse add(String name, Integer number){
		if(StringUtil.isBlank(name) || number == null){
			return ApiResponse.buildRep(1000, null, "参数错误");
		}
		tableService.add(name, number);
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
	
	@RequestMapping(value = "/getCode", method = RequestMethod.GET)
	public ApiResponse getCode(String tableId, HttpServletRequest request,HttpServletResponse response) throws IOException {
		if(StringUtil.isBlank(tableId)){
			return ApiResponse.buildRep(1000, null, "参数错误");
		}
		String token = request.getParameter("token");
		String userId = RedisUtil.get(token);
		// 二维码内的信息
		String info = userId + "_" + tableId;
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
		return ApiResponse.OK;
	}
	
//	@RequestMapping(value = "/scanCode", method = RequestMethod.GET)
//	public ApiResponse scanCode(){
//		
//		return null;
//	}
}
