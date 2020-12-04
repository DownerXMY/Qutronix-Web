package com.qutronix.cloud.feynmanserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qutronix.cloud.feynmanserver.dao.TdQwsDao;
import com.qutronix.cloud.feynmanserver.entity.MPQwsEntity;
import com.qutronix.cloud.feynmanserver.entity.TdQwsEntity;
import com.qutronix.cloud.feynmanserver.service.TdQwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TdQwsServiceImpl implements TdQwsService {

    @Autowired
    TdQwsDao tdQwsDao;

    @Override
    public void save(TdQwsEntity tdQwsEntity) {
        tdQwsDao.insert(tdQwsEntity);
    }

    @Override
    public List<TdQwsEntity> returnAll() {
        QueryWrapper<TdQwsEntity> tdQwsEntityQueryWrapper = new QueryWrapper<>();
        List<TdQwsEntity> result = tdQwsDao.selectList(tdQwsEntityQueryWrapper);
        return result;
    }
}
