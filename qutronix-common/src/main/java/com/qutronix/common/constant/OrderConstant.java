package com.qutronix.common.constant;

/**
 * @author zhongqikuan
 * @email zhong.qikuan@163.com
 * @date 2020/8/24 23:13
 */
public class OrderConstant {
    public static final String USER_ORDER_TOKEN_PREFIX="order:token:";

    public enum OrderStatusEum {
        CREATED(0, "创建"),
        ASSIGNED(1, "已分配"),
        RECEIVED(2, "已领取"),
        FINISHED(3, "已完成"),
        CANCLED(4, "已取消"),
        PAYED(6, "已付款"),
        HASERROR(5, "有异常");

        private int code;
        private String msg;

        OrderStatusEum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return this.code;
        }

        public String getMsg() {
            return this.msg;
        }
    }

}
