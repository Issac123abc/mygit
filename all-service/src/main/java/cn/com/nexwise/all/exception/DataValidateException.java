package cn.com.nexwise.all.exception;

/**
 * 数据验证异常类
 * 
 * */
public class DataValidateException extends RuntimeException {

	public DataValidateException(String message) {
		super(message);
	}
}
