package com.qutronix.cloud.feynmanserver.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("mpqws")
public class MPQwsEntity {

    @TableId
    private int id;

    private String statMethod;
    private String iniRawState;
    private String distance;
    private String probRawStates;
    private String perspectives;
    private String colorbar;
    private String photonNumber;
    private String mode;
    private String uuid;
}
