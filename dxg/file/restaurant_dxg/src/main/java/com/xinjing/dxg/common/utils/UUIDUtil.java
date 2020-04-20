package com.xinjing.dxg.common.utils;

import java.util.UUID;

public class UUIDUtil {

    /**
     * 随机生成UUID字符串
     *
     * @return
     */
    public static String uuid() {
    	String uuid = UUID.randomUUID().toString();
		String replace = uuid.replace("-", "");
		return replace;
    }

    
}
