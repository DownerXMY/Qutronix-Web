package com.qutronix.cloud.feynmanserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;

/**
 * @author zhongqikuan
 * @email zhong.qikuan@163.com
 * @date 2020/11/18 22:43
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QwsResultDTO {
    private String fileName;
}
