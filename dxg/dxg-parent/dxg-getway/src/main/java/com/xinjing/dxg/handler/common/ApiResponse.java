package com.xinjing.dxg.handler.common;

public class ApiResponse<T> {
	public static ApiResponse<Object> OK = new ApiResponse<Object>(0, "成功");
	private int code;
	private T data;
	private String promptMsg;

	public ApiResponse() {
		super();
	}

	public ApiResponse(int code, T data, String promptMsg) {
		this.code = code;
		this.data = data;
		this.promptMsg = promptMsg;
	}

	public ApiResponse(int code, T data) {
		this.code = code;
		this.data = data;
		this.promptMsg = null;
	}

	public ApiResponse(int code, String promptMsg) {
		this.code = code;
		this.data = null;
		this.promptMsg = promptMsg;
	}

	public static <T> ApiResponse<T> buildRightRep(T data, String msg) {
		return new ApiResponse<T>(0, data, msg);
	}

	public static <T> ApiResponse<T> buildRep(Integer code, T data, String msg) {
		return new ApiResponse<T>(code, data, msg);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getPromptMsg() {
		return promptMsg;
	}

	public void setPromptMsg(String promptMsg) {
		this.promptMsg = promptMsg;
	}

	@Override
	public String toString() {
		return "ApiResponse [code=" + code + ", data=" + data + ", promptMsg="
				+ promptMsg + "]";
	}
}
