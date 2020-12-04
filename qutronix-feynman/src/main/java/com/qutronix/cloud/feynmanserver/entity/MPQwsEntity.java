package com.qutronix.cloud.feynmanserver.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("mpqws")
public class MPQwsEntity {

    @TableId
    private int id;

    private String stat_method;
    private String iniRawState;
    private String distance;
    private String probRawStates;
    private String perspectives;
    private String colorbar;
    private String photon_number;
    private String mode;
    private String uuid;
}
