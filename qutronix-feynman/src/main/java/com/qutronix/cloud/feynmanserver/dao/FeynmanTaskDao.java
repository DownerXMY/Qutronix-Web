package com.qutronix.cloud.feynmanserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qutronix.cloud.feynmanserver.entity.FeynmanTaskEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhongqikuan
 * @email zhong.qikuan@163.com
 * @date 2020/12/6 23:15
 */
@Mapper
public interface FeynmanTaskDao extends BaseMapper<FeynmanTaskEntity> {
}
