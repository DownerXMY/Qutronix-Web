package com.qutronix.cloud.feynmanserver.service.impl;

import com.mathworks.toolbox.javabuilder.MWException;
import com.qutronix.cloud.feynman.JQws;
import com.qutronix.cloud.feynmanserver.config.FeynmanConfig;
import com.qutronix.cloud.feynmanserver.dto.BS_dataForm;
import com.qutronix.cloud.feynmanserver.dto.BosonResultDTO;
import com.qutronix.cloud.feynmanserver.service.FeynmanService3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("feynmanService3")
@Slf4j
public class FeynmanServiceImpl3 implements FeynmanService3 {
    @Autowired
    private FeynmanConfig feynmanConfig;
    @Override
    public void feynmanTest() throws MWException {

    }

    @Override
    public String plot1(BS_dataForm bs_dataForm) throws MWException {
        String file =feynmanConfig.filePath+bs_dataForm.getUuid()+".jpg";
        JQws jQws = new JQws();

        return bs_dataForm.getUuid();
    }

    @Override
    public BosonResultDTO plot2(BS_dataForm bs_dataForm) throws MWException {
        String waveguides=bs_dataForm.getUuid()+"_waveguides";
        String distribution=bs_dataForm.getUuid()+"_distribution";
        String waveguidesFile = feynmanConfig.filePath+waveguides+feynmanConfig.fileSuffix;
        String distributionFile = feynmanConfig.filePath+distribution+feynmanConfig.fileSuffix;
        JQws jQws = new JQws();
        jQws.j_boson(bs_dataForm.getInputNum(),bs_dataForm.getFeature(),
                bs_dataForm.getIniState(),waveguidesFile,distributionFile);
        BosonResultDTO bosonResultDTO = BosonResultDTO.builder().distribution(distribution)
                .waveguides(waveguides).build();
        return bosonResultDTO;
    }

    @Override
    public String plot3(BS_dataForm bs_dataForm) throws MWException {
        String file = "/Users/mingyuexu/Desktop/TestPics"+bs_dataForm.getUuid()+".jpg";
        JQws jQws = new JQws();

        return bs_dataForm.getUuid();
    }
}
