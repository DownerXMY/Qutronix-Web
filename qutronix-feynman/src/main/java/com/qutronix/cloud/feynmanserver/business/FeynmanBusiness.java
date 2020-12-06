package com.qutronix.cloud.feynmanserver.business;

import com.qutronix.cloud.feynmanserver.entity.FeynmanTaskEntity;
import com.qutronix.cloud.feynmanserver.service.FeynmanService;
import com.qutronix.cloud.feynmanserver.service.FeynmanTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author zhongqikuan
 * @email zhong.qikuan@163.com
 * @date 2020/12/6 23:33
 */
@Service
public class FeynmanBusiness {
    private static final Integer EXECUTE=0;
    private static final Integer FAIL=2;
    private static final Integer SUCCESS=1;
    @Autowired
    FeynmanTaskService feynmanTaskService;

    public FeynmanTaskEntity builderFeynmanTask(String id,String type,String executor)
    {
        FeynmanTaskEntity feynmanTaskEntity=new FeynmanTaskEntity();
        feynmanTaskEntity.setTaskId(id);
        feynmanTaskEntity.setFeynmanType(type);
        feynmanTaskEntity.setBeginTime(LocalDateTime.now());
        feynmanTaskEntity.setExecutor(executor);
        feynmanTaskEntity.setStatus(EXECUTE);
        feynmanTaskEntity.setRemark("Executing");
        return feynmanTaskEntity;
    }

    public void saveFeynmanTask(FeynmanTaskEntity feynmanTaskEntity)
    {
        feynmanTaskService.saveFeynmanTask(feynmanTaskEntity);
    }

    public void updateFeynmanTaskFailed(FeynmanTaskEntity feynmanTaskEntity)
    {
        feynmanTaskEntity.setEndTime(LocalDateTime.now());
        feynmanTaskEntity.setStatus(FAIL);
        feynmanTaskEntity.setRemark("Fail");
        feynmanTaskService.updateFeynmanTask(feynmanTaskEntity);
    }

    public void updateFeynmanTaskSuccess(FeynmanTaskEntity feynmanTaskEntity)
    {
        feynmanTaskEntity.setEndTime(LocalDateTime.now());
        feynmanTaskEntity.setStatus(SUCCESS);
        feynmanTaskEntity.setRemark("Success");
        feynmanTaskService.updateFeynmanTask(feynmanTaskEntity);
    }
}
