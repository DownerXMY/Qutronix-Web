package com.qutronix.cloud.feynmanserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.mathworks.toolbox.javabuilder.*;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2

@MapperScan("com.qutronix.cloud.feynmanserver.dao")
public class QutronixFeynmanApplication {

    public static void main(String[] args) {
        SpringApplication.run(QutronixFeynmanApplication.class, args);
    }

}
