package com.qutronix.common.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author zhongqikuan
 * @email zhong.qikuan@163.com
 * @date 2020/4/25 13:29
 */
@AllArgsConstructor
@NoArgsConstructor
public enum BizExceptionCodeEnum {
    /**
     *
     */
    UNKNOWN_EXCEPTION(10000, "系统未知异常"),
    /**
     *
     */
    VALID_EXCEPTION(10001, "数据校验异常"),
    /**
     * 短信验证码获取频率太高
     */
    SMS_CODE_EXCEPTION(10002, "短信验证码获取频率太高"),
    /**
     * 请求流量过大
     */
    TOO_MANNY_REQUEST(10003, "请求流量过大"),
    PRODUCT_UP_EXCEPTION(11000, "商品上架异常"),
    USER_EXIST_EXCEPTION(15001, "用户存在异常"),
    PHONE_EXIST_EXCEPTION(15002, "手机号存在异常"),

    NO_STOCK_EXCEPTION(21000,"商品库存不足");

    /**
     * 异常编码
     */
    private int code;
    /**
     * 异常消息
     */
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
