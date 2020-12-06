package com.qutronix.cloud.feynmanserver.dto;

import com.google.gson.JsonArray;
import lombok.Data;

/**
 * @author zhongqikuan
 * @email zhong.qikuan@163.com
 * @date 2020/11/18 14:40
 */
@Data
public class QwsFileDTO {
    private String uuid;

    private String inn;

    private String fz;

    private TableDataDTO[] tabledata;

    private String colorbar;

    private String executor;

}
