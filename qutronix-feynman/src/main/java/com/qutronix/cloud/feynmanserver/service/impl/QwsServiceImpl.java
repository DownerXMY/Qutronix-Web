package com.qutronix.cloud.feynmanserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qutronix.cloud.feynmanserver.dao.QwsDao;
import com.qutronix.cloud.feynmanserver.entity.QwsEntity;
import com.qutronix.cloud.feynmanserver.service.QwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QwsServiceImpl implements QwsService {

    @Autowired
    QwsDao qwsDao;

    @Override
    public void save(QwsEntity qwsEntity) {
        qwsDao.insert(qwsEntity);
    }

    @Override
    public List<QwsEntity> selectAll() {
        QueryWrapper<QwsEntity> qwsEntityQueryWrapper = new QueryWrapper<>();
        List<QwsEntity> result = qwsDao.selectList(qwsEntityQueryWrapper);
        return result;
    }
}
