package com.qutronix.cloud.feynmanserver.service;

import com.mathworks.toolbox.javabuilder.MWException;
import com.qutronix.cloud.feynmanserver.dto.QwsDTO;
import com.qutronix.cloud.feynmanserver.dto.QwsFileDTO;

/**
 * 
 *
 * @author zhongikuan
 * @email zhongqikuan@qutronix.com
 * @date 2020-10-27 15:16:38
 */
public interface FeynmanService  {

   void feynmanTest() throws MWException;

   String plot(QwsDTO qwsDTO) throws MWException;

   String plotFile(QwsFileDTO qwsFileDTO) throws MWException;
}

