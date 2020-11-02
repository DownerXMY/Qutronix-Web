package com.qutronix.common.constant;

/**
 * @author zhongqikuan
 * @email zhong.qikuan@163.com
 * @date 2020/5/1 10:09
 */
public class WareConstant {
    public enum PurchaseStatusEum {
        CREATED(0, "创建"),
        ASSIGNED(1, "已分配"),
        RECEIVED(2, "已领取"),
        FINISHED(3, "已完成"),
        HASERROR(4, "有异常");
        private int code;
        private String msg;

        PurchaseStatusEum(int code, String msg) {
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
    public enum PurchaseDetailStatusEum {
        CREATED(0, "创建"),
        ASSIGNED(1, "已分配"),
        BUYING(2, "正在采购"),
        FINISHED(3, "已完成"),
        HASERROR(4, "采购失败");
        private int code;
        private String msg;

        PurchaseDetailStatusEum(int code, String msg) {
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
