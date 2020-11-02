package com.qutronix.cloud.console.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qutronix.cloud.console.entity.QcaBaseinfoEntity;
import com.qutronix.common.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author zhongikuan
 * @email zhongqikuan@qutronix.com
 * @date 2020-10-27 15:16:38
 */
public interface QcaBaseinfoService extends IService<QcaBaseinfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

