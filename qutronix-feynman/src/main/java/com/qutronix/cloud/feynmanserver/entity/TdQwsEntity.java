package com.qutronix.cloud.feynmanserver.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tdqws")
public class TdQwsEntity {

    @TableId
    private int id;

    private String uuid;

    private String inputNum;
    private String amplitude;
    private String gap;
    private String fData;
    private String tData;
    private String nodeId;
    private String times;
    private String colorbar;
}
