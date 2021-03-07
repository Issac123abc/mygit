package cn.com.nexwise.common.response;

public class ResponseData<T> {

    public static final int SUCCESSFUL = 1;

    private int success = SUCCESSFUL;

    private int code;

    private String message;

    private T data;

    private ResponseData() {
    }

    public static <T> ResponseData<T> ofSuccess(String message) {
    	return of(true, null, 1, message);
    }
    
    public static <T> ResponseData<T> ofSuccess(T data) {
    	return of(true, data, 1, "");
    }
    
    public static <T> ResponseData<T> ofSuccess(T data, String message) {
    	return of(true, data, 1, message);
    }

    // 业务性错误
    public static <T> ResponseData<T> ofFailed(String message) {
    	return of(false, null, 800, message);
    }
    
    public static <T> ResponseData<T> ofFailed(T data, String message) {
    	return of(false, data, 800, message);
    }
    
    // 提示性错误
    public static <T> ResponseData<T> ofError(String message) {
    	return of(false, null, 900, message);
    }
    
    // 没有权限
    public static <T> ResponseData<T> ofUnAuth(String message) {
    	return of(false, null, 401, message);
    }

    private static <T> ResponseData<T> of(boolean success, T data, int code, String message) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.success = success ? SUCCESSFUL : 0;
        responseData.data = data;
        responseData.code = code;
        responseData.message = message;
        return responseData;
    }

    public int getSuccess() {
        return success;
    }

    public boolean successful() {
        return this.getSuccess() == SUCCESSFUL;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

}
