package com.qutronix.cloud.feynmanserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhongqikuan
 * @email zhong.qikuan@163.com
 * @date 2020/11/18 22:43
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MPResultDTO {
    private String fEnumerate;
    private String fTwoD;
    private String fOneOptic;
    private String fRhoZ;
}
