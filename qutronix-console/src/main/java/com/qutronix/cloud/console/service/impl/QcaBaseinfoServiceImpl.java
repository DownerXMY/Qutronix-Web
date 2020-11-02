package com.qutronix.cloud.console.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qutronix.cloud.console.dao.QcaBaseinfoDao;
import com.qutronix.cloud.console.entity.QcaBaseinfoEntity;
import com.qutronix.cloud.console.service.QcaBaseinfoService;
import com.qutronix.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;

import com.qutronix.common.utils.PageUtils;


@Service("qcaBaseinfoService")
public class QcaBaseinfoServiceImpl extends ServiceImpl<QcaBaseinfoDao, QcaBaseinfoEntity> implements QcaBaseinfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<QcaBaseinfoEntity> page = this.page(
                new Query<QcaBaseinfoEntity>().getPage(params),
                new QueryWrapper<QcaBaseinfoEntity>()
        );

        return new PageUtils(page);
    }

}