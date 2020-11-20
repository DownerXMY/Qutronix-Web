package com.qutronix.cloud.feynmanserver.service.impl;


import com.mathworks.toolbox.javabuilder.MWException;

import com.qutronix.cloud.feynman.JQws;
import com.qutronix.cloud.feynmanserver.dto.QwsDTO;
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

    @Override
    public String plot(QwsDTO qwsDTO) throws MWException {
        String file = "F:\\qutronix\\images\\" + qwsDTO.getUuid() + ".png";
        int x=30;
        int y=10;
        JQws jQws = new JQws();
        jQws.j_qws(qwsDTO.getZ().toString(),qwsDTO.getX().toString(),qwsDTO.getY().toString(),
                qwsDTO.getPx().toString(),qwsDTO.getPy().toString(),qwsDTO.getDx().toString(),qwsDTO.getDy().toString(), "colorbar_coolwarm", file,"30","10");
        return  qwsDTO.getUuid();
    }
}