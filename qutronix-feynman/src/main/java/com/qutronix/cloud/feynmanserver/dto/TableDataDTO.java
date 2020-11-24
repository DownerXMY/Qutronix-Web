package com.qutronix.cloud.feynmanserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhongqikuan
 * @email zhong.qikuan@163.com
 * @date 2020/11/23 17:10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableDataDTO {
    private String x;
    private String y;
}
