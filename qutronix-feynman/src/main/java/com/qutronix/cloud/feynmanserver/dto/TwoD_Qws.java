package com.qutronix.cloud.feynmanserver.dto;

import lombok.Data;

@Data
public class TwoD_Qws {

    private String uuid;

    private Integer inputNum;
    private float amplitude;
    private float interval;
    private float from;
    private float to;
    private String nodeId;
    private Integer times;

}
