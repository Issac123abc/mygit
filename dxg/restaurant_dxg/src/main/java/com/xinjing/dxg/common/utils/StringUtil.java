package com.xinjing.dxg.common.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringUtil {

    public static final char UNDERLINE = '_';
    private static final String EMPTY_STRING = "";

    /**
     * 获取单例的空串
     *
     * @return
     */
    public static String getEmptyString() {
        return EMPTY_STRING;
    }

    /**
     * 給用","号隔开的字符串增加单引号，用户数据库IN查询
     *
     * @param str
     * @return
     */
    public static String addQuote(String Ids) {
        String str = "";
        String[] array = Ids.split(",");
        for (int i = 0; i < array.length; i++) {
            str = str + "'" + array[i] + "',";
        }
        str = str.substring(0, str.length() - 1);
        return str;
    }

    /**
     * 判断给定的字符串是否为NULL或者“”
     *
     * @param string
     * @return
     */
    public static boolean isNullOrEmpty(String string) {
        if (string == null) {
            return true;
        } else {
            if (string.equalsIgnoreCase(""))
                return true;
            else
                return false;
        }
    }

    public static boolean isNull(String string) {
        return StringUtil.isNullOrEmpty(string);
    }

    public static boolean isNotNull(String string) {
        return !isNull(string);
    }

    public static boolean isNullOrEmpty(String... params) {
        for (String string : params) {
            if (string == null) {
                return true;
            } else {
                if (string.equalsIgnoreCase(""))
                    return true;
                else
                    return false;
            }
        }
        return true;
    }

    /**
     * 判断给定的字符对象是否为NULL或者“”
     *
     * @param string
     * @return
     */
    public static boolean isNullOrEmpty(Object string) {
        if (string == null) {
            return true;
        } else {
            if (string.toString().equalsIgnoreCase(""))
                return true;
            else
                return false;
        }
    }

    /**
     * 如果改定的字符串为NULL，那么返回“”
     *
     * @param string
     * @return
     */
    public static String nullToEmpty(String string) {
        if (string == null) {
            return "";
        } else {
            return string;
        }
    }

    /**
     * 如果改定的字符串为NULL，那么返回“”
     *
     * @param string
     * @return
     */
    public static String nullToEmpty(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return obj.toString();
        }
    }

    /**
     * 去左空格
     *
     * @param sSourceString
     * @return
     */
    public static String ltrim(String sSourceString) {
        return ltrim(sSourceString, ' ');
    }

    /**
     * 去左边字符
     *
     * @param sSourceString
     * @param cset
     * @return
     */
    public static String ltrim(String sSourceString, char cset) {
        for (int i = 0; i < sSourceString.length(); i++) {
            if (sSourceString.charAt(i) != cset) {
                return sSourceString.substring(i, sSourceString.length() - i);
            }
        }
        return "";
    }

    /**
     * 去右空格
     *
     * @param sSourceString
     * @return
     */
    public static String rtrim(String sSourceString) {
        return rtrim(sSourceString, ' ');
    }

    /**
     * 去右边字符
     *
     * @param sSourceString
     * @param cset
     * @return
     */
    public static String rtrim(String sSourceString, char cset) {
        for (int i = sSourceString.length() - 1; i >= 0; i--) {
            if (sSourceString.charAt(i) != cset) {
                return sSourceString.substring(0, i + 1);
            }
        }
        return "";
    }

    /**
     * 补零返回字符串
     *
     * @param value
     * @param zeroNumber 零的位数，如：00000，0000000
     * @return
     */
    public static String addZero(Integer value, String zeroNumber) {
        DecimalFormat df = new DecimalFormat(zeroNumber);
        return df.format(value);
    }

    /**
     * 将字符数组转换成数字数组
     *
     * @param ids
     * @return
     */
    public static int[] changeToInt(String[] ids) {
        int idsCount = ids.length;
        int[] intIds = new int[idsCount];
        for (int i = 0; i < idsCount; i++) {
            intIds[i] = Integer.valueOf(ids[i]);
        }
        return intIds;
    }

    /**
     * 将字符数组转换成Integer数组
     *
     * @param ids
     * @return
     * @author lihaishan
     */
    public static Integer[] changeToInteger(String[] ids) {
        int idsCount = ids.length;
        Integer[] intIds = new Integer[idsCount];
        for (int i = 0; i < idsCount; i++) {
            intIds[i] = Integer.valueOf(ids[i]);
        }
        return intIds;
    }

    /**
     * 对象为空即返回空字符串
     *
     * @param object
     * @return
     * @Title: NullToString
     * @Description:
     * @author zhengcx
     * @date 2015年5月26日 下午5:28:33
     */
    public static String NullToString(Object object) {
        if (object == null || "".equals(object.toString())) {
            return "";
        }
        return object.toString();
    }

    /**
     * @param string
     * @return
     * @Title: isEmptyOrNull
     * @Description: 判断字符串是否为null “” “null”
     * @author xuk
     * @date 2017年3月6日 下午2:56:36
     */
    public static boolean isEmptyOrNull(String string) {
        if (string == null || "null".equals(string) || "".equals(string)) {
            return true;
        }
        return false;
    }

    /**
     * @param str
     * @return
     * @Title: isBlank
     * @Description: 判断是否为空或者空字符串
     * @author zhangxw
     * @date 2017年4月7日 上午10:25:32
     */
    public static Boolean isBlank(String str) {
        return str == null || str.trim().equals("");
    }

    /**
     * @param arg1
     * @param arg2
     * @return
     * @Title: compare
     * @Description: 比较两个字符串的大小，字符串短的属于小的
     * @author zhangxw
     * @date 2017年4月13日 下午7:25:39
     */
    public static int compare(String arg1, String arg2) {
        int argl1 = arg1.length();
        int argl2 = arg2.length();
        int result = 0;
        for (int i = 0; i < argl1; i++) {
            if (i == (argl1 - 1) && i == (argl2 - 1)) {
                char argc1 = arg1.charAt(i);
                char argc2 = arg2.charAt(i);
                result = argc1 == argc2 ? 0 : argc1 > argc2 ? 1 : -1;
            } else if (i == (argl1 - 1) && i < (argl2 - 1)) {
                result = -1;
            } else if (i >= (argl2 - 1)) {
                result = 1;
            } else {
                char argc1 = arg1.charAt(i);
                char argc2 = arg2.charAt(i);
                if (argc1 == argc2) {
                    continue;
                } else {
                    return argc1 > argc2 ? 1 : -1;
                }
            }
        }
        return result;
    }

    /**
     * @param arg1
     * @param arg2
     * @return
     * @throws Exception
     * @Title: compareHex
     * @Description: 比较两个16进制字符串的大小
     * @author zhangxw
     * @date 2017年4月13日 下午7:30:36
     */
    public static int compareHex(String arg1, String arg2) throws Exception {
        if (arg1.length() <= 64 && arg2.length() <= 64) {
            Long v1 = Long.valueOf(arg1, 16);
            Long v2 = Long.valueOf(arg2, 16);
            return v1 == v2 ? 0 : v1 > v2 ? 1 : -1;
        }
        throw new Exception("String too long");
    }

    public static void main(String[] args) {
        System.out.println(compare("acb", "ac-"));
    }


    /**
     * @param bytes
     * @return
     * @Author zhangxw
     * bytes2Hex: byte转16进制字符串
     */
    public static String bytes2Hex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().toUpperCase();
    }

    /**
     * @param hex
     * @return
     * @Author zhangxw
     * hex2Bytes2: 16进制转byte数组
     */
    public static byte[] hex2Bytes2(String hex) {
        String idx = "0123456789ABCDEF";
        if (hex == null || hex.trim().equals("")) {
            return null;
        }
        hex = hex.toUpperCase();
        int length = hex.length() / 2;
        char[] hexChars = hex.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (idx.indexOf(hexChars[pos]) << 4 | idx.indexOf(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * @param hex
     * @return
     * @Author zhangxw
     * hex2Bytes: 16进制转byte数组
     */
    public static byte[] hex2Bytes(String hex) {
        if (hex.length() < 1)
            return null;
//		hex = hex.toUpperCase();
        byte[] result = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length() / 2; i++) {
            int high = Integer.parseInt(hex.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hex.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static Boolean notNullOrBlank(Object str) {
        if (str != null && !"".equals(str.toString())) {
            return true;
        }
        return false;
    }

    /**
     * @return
     * @Author zhangxw
     * isNotBlank: 判断是否为空白字符
     */
    public static Boolean isNotBlank(String str) {
        return str != null && !str.trim().equals("");
    }

    public static Integer nullToInteger(Object obj) {
        if (obj == null) {
            return 0;
        } else {
            return Integer.parseInt(obj.toString());
        }
    }

    /**
     * solr返回值处理
     *
     * @param
     * @return string
     */
    public static String arrayNullToEmpty(Object obj) {
        if (obj == null) {
            return "";
        } else {
            String returnStr = "";
            String objStr = obj.toString();
            if (objStr.length() > 0 && objStr.indexOf("[") != -1) {
                objStr = objStr.substring(1, objStr.length() - 1);
                if (objStr.indexOf(",") != -1) {
                    String[] arr = objStr.split(",");
                    for (int i = 0; i < arr.length; i++) {
                        returnStr = returnStr + arr[i] + ",";
                    }
                    if (returnStr.length() > 0) {
                        returnStr = returnStr.substring(0,
                                returnStr.length() - 1);
                    }
                } else {
                    returnStr = objStr;
                }
            } else {
                returnStr = objStr;
            }
            return returnStr;
        }
    }

    public static Set<String> arrayNullToEmptySet(Object obj) {
        Set<String> result = new HashSet<String>();
        if (obj == null) {
            return result;
        } else {
            String objStr = obj.toString();
            if (objStr.length() > 0 && objStr.indexOf("[") != -1) {
                objStr = objStr.substring(1, objStr.length() - 1);
                if (objStr.indexOf(",") != -1) {
                    String[] arr = objStr.split(",");
                    for (String sub : arr) {
                        result.add(sub.trim());
                    }
//					result.addAll(Arrays.asList(arr));
                } else {
                    result.add(objStr.trim());
                }
            } else if (objStr.indexOf("[") == -1) {
                result.add(objStr.trim());
            }
        }
        return result;
    }

    public static List<Integer> getIndex(String str) {
        List<Integer> ls = new ArrayList<>();
        if (str.indexOf("(") != -1 && str.indexOf(")") != -1) {
            ls.add(str.indexOf("(") + 1);
            ls.add(str.indexOf(")"));
        } else if (str.indexOf("（") != -1 && str.indexOf("）") != -1) {
            ls.add(str.indexOf("（") + 1);
            ls.add(str.indexOf("）"));
        }
        return ls;
    }

    public static String getString(Object object) {
        if (object == null || "".equals(object.toString().trim())) {
            return "";
        }
        return object.toString().trim();
    }

    public static String join(Set<String> set, String separator) {
        if (set != null && set.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (String s : set) {
                sb.append(s);
                sb.append(separator);
            }
            return sb.toString().substring(0, sb.length() - 1);
        }
        return "";
    }
    
    public static String join(Collection<String> collection, String separator){
    	if (collection != null && collection.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (String s : collection) {
                sb.append(s);
                sb.append(separator);
            }
            return sb.toString().substring(0, sb.length() - 1);
        }
        return "";
    }

    public static boolean isNullOrBlank(Object string) {
        if (string == null) {
            return true;
        } else {
            if (string.toString().trim().equalsIgnoreCase(""))
                return true;
            else
                return false;
        }
    }

    /**
     * 下划线格式字符串转换为驼峰格式字符串,去掉首字母F_
     *
     * @param str
     * @return
     */
    public static String underlineToCamelSubF_(String str) {
        if (str == null || "".equals(str.trim())) {
            return "";
        }
        if (str.startsWith("F_")) {
            str = str.substring(2, str.length());
        }
        str = str.toLowerCase();
        int len = str.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (ch == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(str.charAt(i)));
                }
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static String nullToTip(Object obj) {
        if (obj == null) {
            return "无";
        } else {
            return obj.toString();
        }
    }
    
    /**
     * 判断是否字母或数字
     * 
     * */
    public static boolean isLetterDigit(String str) {
    	  String regex = "^[a-z0-9A-Z]+$";
    	  return str.matches(regex);
    }
    
}
