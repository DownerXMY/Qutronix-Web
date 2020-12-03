package com.qutronix.cloud.feynmanserver.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("boson")
public class BosonEntity {

    @TableId
    private int id;

    private String uuid;
    private String inputNum;
    private String iniRawState;
    private String feature;

}
