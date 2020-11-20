package com.qutronix.cloud.feynmanserver.service;

import com.mathworks.toolbox.javabuilder.MWException;
import com.qutronix.cloud.feynmanserver.dto.TwoD_Qws;

public interface FeynmanService2 {
    void feynmanTest() throws MWException;
    String plot2(TwoD_Qws twoD_qws) throws MWException;
    String plot3(TwoD_Qws twoD_qws) throws MWException;
}
