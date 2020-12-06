package com.qutronix.cloud.feynmanserver.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zhongqikuan
 * @email zhong.qikuan@163.com
 * @date 2020/12/6 23:04
 */
@Data
@TableName("feynmantask")
public class FeynmanTaskEntity {
    @TableId
    private Integer id;
    private String taskId;
    private String feynmanType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime beginTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;
    private BigDecimal executeTime;
    private Integer status;
    private String remark;
    private String executor;
}
