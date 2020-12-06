package com.qutronix.cloud.feynmanserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qutronix.cloud.feynmanserver.dao.FeynmanTaskDao;
import com.qutronix.cloud.feynmanserver.entity.FeynmanTaskEntity;
import com.qutronix.cloud.feynmanserver.service.FeynmanTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhongqikuan
 * @email zhong.qikuan@163.com
 * @date 2020/12/6 23:20
 */
@Service
public class FeynmanTaskServiceImpl implements FeynmanTaskService {

    @Resource
    private FeynmanTaskDao feynmanTaskDao;

    @Override
    public void saveFeynmanTask(FeynmanTaskEntity feynmanTaskEntity) {
        feynmanTaskDao.insert(feynmanTaskEntity);


    }

    @Override
    public void updateFeynmanTask(FeynmanTaskEntity feynmanTaskEntity) {
        QueryWrapper<FeynmanTaskEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(FeynmanTaskEntity::getTaskId, feynmanTaskEntity.getTaskId());
        feynmanTaskDao.update(feynmanTaskEntity,queryWrapper);
    }

    @Override
    public List<FeynmanTaskEntity> getAllFeynmanTask(String type, String executor) {
        QueryWrapper<FeynmanTaskEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(FeynmanTaskEntity::getFeynmanType, type)
                .eq(FeynmanTaskEntity::getExecutor, executor)
        .orderByDesc(FeynmanTaskEntity::getBeginTime);
        List<FeynmanTaskEntity> feynmanTaskEntities = feynmanTaskDao.selectList(queryWrapper);
        return feynmanTaskEntities;
    }
}
