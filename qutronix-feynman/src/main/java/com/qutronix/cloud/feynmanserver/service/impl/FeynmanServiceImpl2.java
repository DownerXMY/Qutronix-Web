package com.qutronix.cloud.feynmanserver.service.impl;

import com.mathworks.toolbox.javabuilder.MWException;
import com.qutronix.cloud.feynman.JQws;
import com.qutronix.cloud.feynmanserver.config.FeynmanConfig;
import com.qutronix.cloud.feynmanserver.dto.QwsResultDTO;
import com.qutronix.cloud.feynmanserver.dto.TwoD_Qws;
import com.qutronix.cloud.feynmanserver.service.FeynmanService2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("feynmanService2")
@Slf4j
public class FeynmanServiceImpl2 implements FeynmanService2 {
    @Autowired
    private FeynmanConfig feynmanConfig;

    @Override
    public void feynmanTest() throws MWException {
        log.info("feynmanTest2");
        JQws jQws = new JQws();
        jQws.j_qws(15, "colorbar_coolwarm", "/Users/mingyuexu/Desktop/TestPics2/test.jpg");
        log.info("feynmanTest2 over");
    }

    @Override
    public String plot2(TwoD_Qws twoD_qws) throws MWException {
        String file = "/Users/mingyuexu/Desktop/TestPics" + twoD_qws.getUuid() + ".jpg";
        JQws jQws = new JQws();
        return twoD_qws.getUuid();
    }

    @Override
    public QwsResultDTO plot3(TwoD_Qws twoD_qws) throws MWException {
        log.info("twoD_qws={}", twoD_qws);
        String colorbar = "colorbar_coolwarm";
        if (StringUtils.isNotEmpty(twoD_qws.getColorbar())) {
            colorbar = twoD_qws.getColorbar();
        }
        String filePath = feynmanConfig.filePath+ twoD_qws.getUuid() + feynmanConfig.fileSuffix;// "/Users/mingyuexu/Desktop/TestPics2"+twoD_qws.getUuid()+".jpg";
        String figureName= twoD_qws.getUuid() + "_f1";
        String figurePath = feynmanConfig.filePath + figureName +feynmanConfig.fileSuffix;
        JQws jQws = new JQws();
        jQws.j_qsws(twoD_qws.getFData(), twoD_qws.getTData(),
                twoD_qws.getGap(), twoD_qws.getAmplitude(),
                twoD_qws.getInputNum(), twoD_qws.getNodeId(), colorbar,
                filePath,figurePath, "30", "10");
        QwsResultDTO resultDTO  = QwsResultDTO.builder().fileName(twoD_qws.getUuid())
                .figure1(figureName).build();
        return resultDTO;
    }
}
