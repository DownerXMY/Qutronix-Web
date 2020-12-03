package com.qutronix.cloud.feynmanserver.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("qws")
public class QwsEntity {

    @TableId
    private int id;

    private String z;
    private String x;
    private String y;
    private String px;
    private String py;
    private String dx;
    private String dy;
    private String fz;
    private String inn;
    private String uuid;
}
