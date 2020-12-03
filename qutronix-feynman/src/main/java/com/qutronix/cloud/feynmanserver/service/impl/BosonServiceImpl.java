package com.qutronix.cloud.feynmanserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qutronix.cloud.feynmanserver.dao.BosonDao;
import com.qutronix.cloud.feynmanserver.entity.BosonEntity;
import com.qutronix.cloud.feynmanserver.entity.QwsEntity;
import com.qutronix.cloud.feynmanserver.service.BosonService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BosonServiceImpl implements BosonService {

    @Autowired
    BosonDao bosonDao;

    @Override
    public void save(BosonEntity bosonEntity) {
        bosonDao.insert(bosonEntity);
    }

    @Override
    public List<BosonEntity> returnAll() {
        QueryWrapper<BosonEntity> bosonEntityQueryWrapper = new QueryWrapper<>();
        List<BosonEntity> result = bosonDao.selectList(bosonEntityQueryWrapper);
        return result;
    }
}
