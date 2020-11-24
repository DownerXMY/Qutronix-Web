package com.qutronix.cloud.feynmanserver.service.impl;


import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWComplexity;
import com.mathworks.toolbox.javabuilder.MWException;

import com.mathworks.toolbox.javabuilder.MWNumericArray;
import com.qutronix.cloud.feynman.JQws;
import com.qutronix.cloud.feynmanserver.dto.QwsDTO;
import com.qutronix.cloud.feynmanserver.dto.QwsFileDTO;
import com.qutronix.cloud.feynmanserver.dto.TableDataDTO;
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
        JQws jQws = new JQws();
        jQws.j_qws(qwsDTO.getZ().toString(), qwsDTO.getX().toString(), qwsDTO.getY().toString(),
                qwsDTO.getPx().toString(), qwsDTO.getPy().toString(), qwsDTO.getDx().toString(), qwsDTO.getDy().toString(), "colorbar_coolwarm", file, "30", "10");
        return qwsDTO.getUuid();
    }

    @Override
    public String plotFile(QwsFileDTO qwsFileDTO) throws MWException {
        String file = "F:\\qutronix\\images\\" + qwsFileDTO.getUuid() + ".png";
        int n = qwsFileDTO.getTabledata().length;
        int[] dims = {1, n};
        MWNumericArray x = null; // 存放x值的数组
        MWNumericArray y = null; // 存放y值的数组
        x = MWNumericArray.newInstance(dims, MWClassID.DOUBLE,
                MWComplexity.REAL);
        y = MWNumericArray.newInstance(dims, MWClassID.DOUBLE,
                MWComplexity.REAL);
        JQws jQws = new JQws();

        // 定义 y = x^2
        for (int i = 0; i <= qwsFileDTO.getTabledata().length - 1; i++) {
            TableDataDTO tableDataDTO = qwsFileDTO.getTabledata()[i];
            x.set(i + 1, Double.parseDouble(tableDataDTO.getX()));
            y.set(i + 1, Double.parseDouble(tableDataDTO.getY()));
        }
        jQws.j_qws_f(qwsFileDTO.getFz(), qwsFileDTO.getInn(), x, y, "colorbar_coolwarm", file, "30", "10");
        return qwsFileDTO.getUuid();
    }
}