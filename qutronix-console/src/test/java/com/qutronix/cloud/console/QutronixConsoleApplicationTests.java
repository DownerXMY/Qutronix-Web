package com.qutronix.cloud.console;

import com.qutronix.cloud.console.entity.QcaBaseinfoEntity;
import com.qutronix.cloud.console.service.QcaBaseinfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class QutronixConsoleApplicationTests {
    @Autowired
    QcaBaseinfoService qcaBaseinfoService;

    @Test
    void contextLoads() {
    }

    @Test
    void saveBaseInfo() {
        log.info("saveBaseInfo");
        QcaBaseinfoEntity qcaBaseinfoEntity = new QcaBaseinfoEntity();
        qcaBaseinfoEntity.setName("baseinfo test");
        qcaBaseinfoService.save(qcaBaseinfoEntity);
    }

}
