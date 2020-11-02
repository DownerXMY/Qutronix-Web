package com.qutronix.common.utils;

/**
 * @author zhongqikuan
 * @email zhong.qikuan@163.com
 * @date 2020/5/5 17:14
 */
public class ValidateUtils {

    public static  boolean isNotZero(String value)
    {
        return  !"0".equalsIgnoreCase(value);
    }
}
