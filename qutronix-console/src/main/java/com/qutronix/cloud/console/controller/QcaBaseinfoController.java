package com.qutronix.cloud.console.controller;

import java.util.Arrays;
import java.util.Map;


import com.qutronix.cloud.console.entity.QcaBaseinfoEntity;
import com.qutronix.cloud.console.service.QcaBaseinfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.qutronix.common.utils.PageUtils;
import com.qutronix.common.utils.R;



/**
 * 
 *
 * @author zhongikuan
 * @email zhongqikuan@qutronix.com
 * @date 2020-10-27 15:16:38
 */
@RestController
@RequestMapping("console/qcabaseinfo")
@Api(tags = "思量云控制台")
public class QcaBaseinfoController {
    @Autowired
    private QcaBaseinfoService qcaBaseinfoService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "数据列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = qcaBaseinfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @PostMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		QcaBaseinfoEntity qcaBaseinfo = qcaBaseinfoService.getById(id);

        return R.ok().put("qcaBaseinfo", qcaBaseinfo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody QcaBaseinfoEntity qcaBaseinfo){
		qcaBaseinfoService.save(qcaBaseinfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody QcaBaseinfoEntity qcaBaseinfo){
		qcaBaseinfoService.updateById(qcaBaseinfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		qcaBaseinfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
