package com.qutronix.cloud.feynmanserver.service;

import com.qutronix.cloud.feynmanserver.entity.TdQwsEntity;

import java.util.List;

public interface TdQwsService {

    void save (TdQwsEntity tdQwsEntity);

    List<TdQwsEntity> returnAll();
}
