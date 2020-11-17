package com.qutronix.cloud.feynmanserver.service.impl;


import com.mathworks.toolbox.javabuilder.MWException;
import com.qutronix.cloud.feynman.JQws;
import com.qutronix.cloud.feynmanserver.service.FeynmanService;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service("feynmanService")
@Slf4j
public class FeynmanServiceImpl implements FeynmanService {

    @Override
    public void feynmanTest() throws MWException {
        log.info("feynmanTest");
        JQws jQws = new JQws();
        jQws.j_qws(15, "colorbar_coolwarm", "F:\\qutronix\\images\\tt.png");
        log.info("feynmanTest over");
    }
}