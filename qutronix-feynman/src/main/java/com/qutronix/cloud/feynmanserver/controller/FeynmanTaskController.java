package com.qutronix.cloud.feynmanserver.controller;


import com.qutronix.cloud.feynmanserver.business.FeynmanBusiness;
import com.qutronix.cloud.feynmanserver.dto.*;
import com.qutronix.cloud.feynmanserver.entity.FeynmanTaskEntity;
import com.qutronix.cloud.feynmanserver.entity.QwsEntity;
import com.qutronix.cloud.feynmanserver.entity.QwsFileEntity;
import com.qutronix.cloud.feynmanserver.service.FeynmanService;
import com.qutronix.cloud.feynmanserver.service.FeynmanTaskService;
import com.qutronix.cloud.feynmanserver.service.QwsFileService;
import com.qutronix.cloud.feynmanserver.service.QwsService;
import com.qutronix.common.result.Result;
import com.qutronix.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author zhongikuan
 * @email zhongqikuan@qutronix.com
 * @date 2020-10-27 15:16:38
 */
@RestController
@RequestMapping("feynman/task")
@Api(tags = "思量云task")
@Slf4j
public class FeynmanTaskController {

    @Autowired
    FeynmanTaskService feynmanTaskService;


    @PostMapping(value = "/list/all")
    public Result<List<FeynmanTaskEntity>> listAllFeynmanTask(@RequestBody FeymanTaskQueryDTO taskQueryDTO) throws Exception {
        log.info("taskQueryDTO={}",taskQueryDTO);
        List<FeynmanTaskEntity> allFeynmanTask = feynmanTaskService.getAllFeynmanTask(taskQueryDTO.getTaskType(), taskQueryDTO.getExecutor());
        return Result.success(allFeynmanTask);
    }



}
