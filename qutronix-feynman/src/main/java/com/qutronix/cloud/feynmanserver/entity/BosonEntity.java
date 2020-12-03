package com.qutronix.cloud.feynmanserver.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class BosonEntity {

    @TableId
    private int id;

    private String uuid;
    private String inputNum;
    private String iniState;
    private String feature;

}
