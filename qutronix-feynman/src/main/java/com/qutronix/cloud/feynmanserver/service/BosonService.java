package com.qutronix.cloud.feynmanserver.service;

import com.qutronix.cloud.feynmanserver.entity.BosonEntity;

import java.util.List;

public interface BosonService {

    void save(BosonEntity bosonEntity);

    List<BosonEntity> returnAll();
}
