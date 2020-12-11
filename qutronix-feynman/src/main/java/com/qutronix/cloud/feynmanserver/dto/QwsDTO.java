package com.qutronix.cloud.feynmanserver.dto;

import lombok.Data;
import org.springframework.boot.actuate.integration.IntegrationGraphEndpoint;

import java.math.BigDecimal;

/**
 * @author zhongqikuan
 * @email zhong.qikuan@163.com
 * @date 2020/11/18 14:40
 */
@Data
public class QwsDTO {
    private String uuid;

    private String z;

    private String x;
    private String y;

    private String px;
    private String py;

    private String dx;
    private String dy;

    /**
     * 30
     */
    private String accx;

    /**
     * accy
     */
    private String accy;

    private String colorbar;

    private String executor;
}
