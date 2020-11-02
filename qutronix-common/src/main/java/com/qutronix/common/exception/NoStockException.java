package com.qutronix.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhongqikuan
 * @email zhong.qikuan@163.com
 * @date 2020/8/28 0:59
 */
public class NoStockException extends RuntimeException {
    @Getter
    @Setter
    private Long skuId;

    public NoStockException(Long skuId) {
        super("商品id=" + skuId + "没有足够的库存了");
    }
}
