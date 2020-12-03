package com.qutronix.cloud.feynmanserver.service;

import com.qutronix.cloud.feynmanserver.entity.QwsEntity;

import java.util.List;

public interface QwsService {
    void save(QwsEntity qwsEntity);
    List<QwsEntity> selectAll();
}
