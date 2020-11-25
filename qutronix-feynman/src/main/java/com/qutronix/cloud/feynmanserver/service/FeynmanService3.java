package com.qutronix.cloud.feynmanserver.service;

import com.mathworks.toolbox.javabuilder.MWException;
import com.qutronix.cloud.feynmanserver.dto.BS_dataForm;

public interface FeynmanService3 {
    void feynmanTest() throws MWException;
    String plot1(BS_dataForm bs_dataForm) throws MWException;
    String plot2(BS_dataForm bs_dataForm) throws MWException;
    String plot3(BS_dataForm bs_dataForm) throws MWException;
}
