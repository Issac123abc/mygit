package com.xinjing.dxg.common.utils;

import java.util.Random;

public class MathUtil {

	public static String smsCode(){
		String smsCode = String.valueOf(new Random().nextInt(899999) + 100000);
		return smsCode;
	}
	
	public static boolean verifySmsCode(String phone, String smsCode){
		if(StringUtil.isBlank(phone) || StringUtil.isBlank(smsCode)){
			return false;
		}
		String code = RedisUtil.get(phone);
		if(smsCode.equals(code)){
			return true;
		}
		return false;
	}
}
