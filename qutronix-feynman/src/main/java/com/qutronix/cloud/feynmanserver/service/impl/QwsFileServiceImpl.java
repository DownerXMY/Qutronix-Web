package com.qutronix.cloud.feynmanserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qutronix.cloud.feynmanserver.dao.QwsFileDao;
import com.qutronix.cloud.feynmanserver.entity.QwsEntity;
import com.qutronix.cloud.feynmanserver.entity.QwsFileEntity;
import com.qutronix.cloud.feynmanserver.service.QwsFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QwsFileServiceImpl implements QwsFileService {

    @Autowired
    QwsFileDao qwsFileDao;

    @Override
    public void save(QwsFileEntity qwsFileEntity) {
        qwsFileDao.insert(qwsFileEntity);
    }

    @Override
    public List<QwsFileEntity> getPoints() {
        QueryWrapper<QwsFileEntity> qwsFileEntityQueryWrapper = new QueryWrapper<>();
        List<QwsFileEntity> result = qwsFileDao.selectList(qwsFileEntityQueryWrapper);
        return result;
    }
}
