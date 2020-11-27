package com.qutronix.cloud.feynmanserver.service.impl;

import com.mathworks.toolbox.javabuilder.MWException;
import com.qutronix.cloud.feynman.JQws;
import com.qutronix.cloud.feynmanserver.dto.BS_dataForm;
import com.qutronix.cloud.feynmanserver.dto.MPQW_dataForm;
import com.qutronix.cloud.feynmanserver.service.FeynmanService4;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("feynmanService4")
@Slf4j
public class FeynmanServiceImpl4 implements FeynmanService4 {
    @Override
    public void feynmanTest() throws MWException {}

    @Override
    public String plot1(MPQW_dataForm mpqw_dataForm) throws MWException {
        String file = "/Users/mingyuexu/Desktop/TestPics"+mpqw_dataForm.getUuid()+".jpg";
        JQws jQws = new JQws();

        return mpqw_dataForm.getUuid();
    }
}
