package com.qutronix.cloud.feynmanserver.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhongqikuan
 * @email zhong.qikuan@163.com
 * @date 2020/11/18 14:40
 */
@Data
public class QwsDTO {
    private String uuid;

    private Integer z;

    private Integer x;
    private Integer y;

    private Integer px;
    private Integer py;

    private Integer dx;
    private Integer dy;

}
