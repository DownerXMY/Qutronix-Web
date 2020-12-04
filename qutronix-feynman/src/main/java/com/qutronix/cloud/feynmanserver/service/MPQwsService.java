package com.qutronix.cloud.feynmanserver.service;

import com.qutronix.cloud.feynmanserver.entity.MPQwsEntity;

import java.util.List;

public interface MPQwsService {

    void save(MPQwsEntity mpQwsEntity);

    List<MPQwsEntity> returnAll();
}
