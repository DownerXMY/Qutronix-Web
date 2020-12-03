package com.qutronix.cloud.feynmanserver.service;

import com.qutronix.cloud.feynmanserver.entity.QwsFileEntity;

import java.util.List;

public interface QwsFileService {

    void save(QwsFileEntity qwsFileEntity);

    List<QwsFileEntity> getPoints();
}
