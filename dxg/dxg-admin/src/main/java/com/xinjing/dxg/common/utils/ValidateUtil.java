package com.xinjing.dxg.common.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class ValidateUtil {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 实体属性验证器
     * 基于springboot提供的validator验证工具
     * 1、使用注解@Valid 或者 调用此方法可实现对实体某些属性非空，长度，自定的规则验证
     *
     * @param obj
     * @return
     */
    public static <T> Map<String, StringBuffer> validate(T obj) {
        Map<String, StringBuffer> errorMap = null;
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (set != null && set.size() > 0) {
            errorMap = new HashMap<String, StringBuffer>();
            String property = null;
            for (ConstraintViolation<T> cv : set) {
                //这里循环获取错误信息，可以自定义格式  
                property = cv.getPropertyPath().toString();
                if (errorMap.get(property) != null) {
                    errorMap.get(property).append("," + cv.getMessage());
                } else {
                    StringBuffer sb = new StringBuffer();
                    sb.append(cv.getMessage());
                    errorMap.put(property, sb);
                }
            }
        }
        return errorMap;
    }

    /**
     * @param isValidDateTime 验证日期字符串符合格式yyyy-MM-dd HH:mm:ss,符合返回true,否则返回false
     * @return
     */
    public static boolean isValidDateTime(String dateStr) {

        boolean isValid = true;
        if (StringUtil.isBlank(dateStr)) {
            return false;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            format.setLenient(false);
            format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            isValid = false;
        }
        return isValid;
    }

    /**
     * @param dateStr 验证日期字符串符合格式yyyy-MM-dd,符合返回true,否则返回false
     * @return
     */
    public static boolean isValidDate(String dateStr) {

        boolean isValid = true;
        if (StringUtil.isBlank(dateStr)) {
            return false;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            format.setLenient(false);
            format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            isValid = false;
        }
        return isValid;
    }


    /**
     * @param dateStr 验证日期字符串符合格式HH:mm:ss,符合返回true,否则返回false
     * @return
     */
    public static boolean isValidTime(String dateStr) {

        boolean isValid = true;
        if (StringUtil.isBlank(dateStr)) {
            return false;
        }
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        try {
            format.setLenient(false);
            format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            isValid = false;
        }
        return isValid;
    }


    /**
     * @param idCardNo
     * @return
     * @Title: isMac
     * @Description: 验证MAC
     * @author wuyy
     * @date 2017年3月2日 下午1:59:26
     */
    public static boolean isMac(String mac) {
        String regex = "[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}";
        return Pattern.matches(regex, mac);
    }

    /**
     * @param idCardNo
     * @return
     * @Title: isIdCardNo
     * @Description: 验证身份证号
     * @author wuyy
     * @date 2017年3月2日 下午1:48:04
     */
    public static boolean isIdCardNo(String idCardNo) {
        String regex = "[1-9]\\d{13}[X0-9]{1}|[1-9]\\d{16}[X0-9]{1}";
        return Pattern.matches(regex, idCardNo);
    }

    /**
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
     *
     * @param mobile 移动、联通、电信运营商的号码段
     *               <p>
     *               移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
     *               、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）
     *               </p>
     *               <p>
     *               联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）
     *               </p>
     *               <p>
     *               电信的号段：133、153、180（未启用）、189
     *               </p>
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean isMobile(String mobile) {
        String regex = "(\\+\\d+)?1[3456789]\\d{9}$";
        return Pattern.matches(regex, mobile);
    }

    /**
     * @param mobile
     * @return
     * @Title: isVehicleNo
     * @Description: 验证是否是车牌号码
     * @author wuyy
     * @date 2017年3月2日 下午1:57:26
     */
    public static boolean isVehicleNo(String vehicleNo) {
        String regex = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";
        return Pattern.matches(regex, vehicleNo);
    }
    
    /**
     * 隐藏身份证号码
     * 
     * */
    public static String hiddenIdCardNo(String idCardNo){
    	if(StringUtil.isEmptyOrNull(idCardNo) || idCardNo.length() < 18){
    		return null;
    	}
    	String replace = idCardNo.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1**********$2");
    	return replace;
    }

}
