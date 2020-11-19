package com.qutronix.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication()
@EnableDiscoveryClient
public class QutronixGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(QutronixGatewayApplication.class, args);
    }

}
