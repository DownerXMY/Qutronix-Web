package com.qutronix.cloud.feynmanserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhongqikuan
 * @email zhong.qikuan@163.com
 * @date 2020/11/25 17:17
 */
@Component
@ConfigurationProperties(prefix = "feynman.server")
@Data
public class FeynmanConfig {
    public  String filePath;
    public  String fileSuffix;
}
