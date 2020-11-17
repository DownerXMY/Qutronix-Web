package com.qutronix.cloud.qgo;

import com.qutronix.cloud.qgo.handler.NettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class QcloudQgoApplication {

    public static void main(String[] args) {

        SpringApplication.run(QcloudQgoApplication.class, args);

        try {
            log.info("http://127.0.0.1:6688/netty-websocket/index");
            new NettyServer(12345).start();
        }catch(Exception e) {
            log.error("NettyServerError:"+e.getMessage());
        }
    }

}
