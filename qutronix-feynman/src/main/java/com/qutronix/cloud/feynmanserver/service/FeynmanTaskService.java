package com.qutronix.cloud.feynmanserver.service;

import com.qutronix.cloud.feynmanserver.entity.FeynmanTaskEntity;

import java.util.List;

/**
 * @author zhongqikuan
 * @email zhong.qikuan@163.com
 * @date 2020/12/6 23:16
 */
public interface FeynmanTaskService {
    void saveFeynmanTask(FeynmanTaskEntity feynmanTaskEntity);

    void updateFeynmanTask(FeynmanTaskEntity feynmanTaskEntity);

    List<FeynmanTaskEntity> getAllFeynmanTask(String type,String executor);
}
