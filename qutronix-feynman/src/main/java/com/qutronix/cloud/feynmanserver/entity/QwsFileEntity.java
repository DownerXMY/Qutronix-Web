package com.qutronix.cloud.feynmanserver.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qutronix.cloud.feynmanserver.dto.TableDataDTO;
import lombok.Data;

@Data
@TableName("qwsMan")
public class QwsFileEntity {

    @TableId
    private int id;

    private String uuid;

    private String fz;
    private String inn;
    private TableDataDTO[] tabledata;
}
