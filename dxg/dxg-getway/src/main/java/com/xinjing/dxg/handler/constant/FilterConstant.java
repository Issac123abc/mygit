package com.xinjing.dxg.handler.constant;

import java.util.ArrayList;
import java.util.List;

public class FilterConstant {
    public static List<String> authNotFilter = new ArrayList<>();

    static {
        authNotFilter.add("/**/login/**");
        authNotFilter.add("/**/register/**");
        authNotFilter.add("/**/smsCode/**");
    }
}
