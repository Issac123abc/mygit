/*
 * Create Date: 2016-7-19 15:10
 * Last Modify Date: 2016-8-3 11:47
 */
package com.xinjing.dxg.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类, 源码来自网上。
 * 
 * @author Liang Xie
 * @version 2014年9月25日 上午9:40:21
 *
 */
public class MD5Util {

    // 全局数组
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f" };

    private MD5Util() {
    }

    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;

        if (iRet < 0) {
            iRet += 256;
        }

        int iD1 = iRet / 16;
        int iD2 = iRet % 16;

        return strDigits[iD1] + strDigits[iD2];
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();

        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }

        return sBuffer.toString();
    }

    /**
     * 获得MD5的值
     * 
     * @param strObj
     *            字符串
     * @return 字符串对应的MD5的值
     */
    public static String GetMD5Code(String strObj) {
        String resultString = null;

        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

        return resultString;
    }

}
