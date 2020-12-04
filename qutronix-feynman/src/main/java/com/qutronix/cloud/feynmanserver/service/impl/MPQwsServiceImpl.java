package com.qutronix.cloud.feynmanserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qutronix.cloud.feynmanserver.dao.MPQwsDao;
import com.qutronix.cloud.feynmanserver.entity.BosonEntity;
import com.qutronix.cloud.feynmanserver.entity.MPQwsEntity;
import com.qutronix.cloud.feynmanserver.service.MPQwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MPQwsServiceImpl implements MPQwsService {

    @Autowired
    MPQwsDao mpQwsDao;

    @Override
    public void save(MPQwsEntity mpQwsEntity) {
        mpQwsDao.insert(mpQwsEntity);
    }

    @Override
    public List<MPQwsEntity> returnAll() {
        QueryWrapper<MPQwsEntity> mpQwsEntityQueryWrapper = new QueryWrapper<>();
        List<MPQwsEntity> result = mpQwsDao.selectList(mpQwsEntityQueryWrapper);
        return result;
    }
}
