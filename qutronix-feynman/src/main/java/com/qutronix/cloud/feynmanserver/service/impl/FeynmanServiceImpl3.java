package com.qutronix.cloud.feynmanserver.service.impl;

import com.mathworks.toolbox.javabuilder.MWException;
import com.qutronix.cloud.feynman.JQws;
import com.qutronix.cloud.feynmanserver.dto.BS_dataForm;
import com.qutronix.cloud.feynmanserver.service.FeynmanService3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("feynmanService3")
@Slf4j
public class FeynmanServiceImpl3 implements FeynmanService3 {

    @Override
    public void feynmanTest() throws MWException {

    }

    @Override
    public String plot1(BS_dataForm bs_dataForm) throws MWException {
        String file = "/Users/mingyuexu/Desktop/TestPics"+bs_dataForm.getUuid()+".jpg";
        JQws jQws = new JQws();

        return bs_dataForm.getUuid();
    }

    @Override
    public String plot2(BS_dataForm bs_dataForm) throws MWException {
        String file = "/Users/mingyuexu/Desktop/TestPics"+bs_dataForm.getUuid()+".jpg";
        JQws jQws = new JQws();

        return bs_dataForm.getUuid();
    }

    @Override
    public String plot3(BS_dataForm bs_dataForm) throws MWException {
        String file = "/Users/mingyuexu/Desktop/TestPics"+bs_dataForm.getUuid()+".jpg";
        JQws jQws = new JQws();

        return bs_dataForm.getUuid();
    }
}
