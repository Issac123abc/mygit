package cn.com.nexwise.all.config;

import cn.com.nexwise.all.exception.DataValidateException;
import cn.com.nexwise.all.response.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局异常处理器
 * 
 * */
@ControllerAdvice(basePackages = {"cn.com.nexwise"})
public class GlobalExceptionController {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseData<Object> fail(Exception e) {
		if (e instanceof DataValidateException) { //自定义异常
			logger.error("DataValidateException异常:{}", e);
			return ResponseData.ofError(e.getMessage());
		} else {// 系统异常
			logger.error("服务器错误:{}", e);
			return ResponseData.ofFailed("服务器内部错误");
		}
	}
}
