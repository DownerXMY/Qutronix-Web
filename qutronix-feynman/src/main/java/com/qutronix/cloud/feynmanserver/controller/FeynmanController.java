package com.qutronix.cloud.feynmanserver.controller;


import com.mathworks.toolbox.javabuilder.MWException;
import com.qutronix.cloud.feynmanserver.business.FeynmanBusiness;
import com.qutronix.cloud.feynmanserver.config.FeynmanConfig;
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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author zhongikuan
 * @email zhongqikuan@qutronix.com
 * @date 2020-10-27 15:16:38
 */
@RestController
@RequestMapping("feynman/server")
@Api(tags = "思量云FeynmanServer")
@Slf4j
public class FeynmanController {

    @Autowired
    FeynmanService feynmanService;

    @Autowired
    QwsService qwsService;

    @Autowired
    QwsFileService qwsFileService;


    @Autowired
    private FeynmanConfig feynmanConfig;

    @Autowired
    FeynmanBusiness feynmanBusiness;

    private static final String FEYNMAN_TYPE = "qws";

    /**
     * 测试代码
     */
    @PostMapping("/test")
    @ApiOperation(value = "测试代码")
    public R list() throws Exception {
        ApplicationHome applicationHome = new ApplicationHome();
        log.info("applicationHome:{}", applicationHome.getDir().getAbsolutePath());
        return R.ok();
    }

    @GetMapping(value = "/result", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public BufferedImage getImage(@RequestParam String fileName) throws IOException {
        return ImageIO.read(new FileInputStream(new File(feynmanConfig.filePath + fileName + feynmanConfig.fileSuffix)));
    }


    @PostMapping(value = "/plot")
    public Result<QwsResultDTO> plot(@RequestBody QwsDTO qwsDTO) throws Exception {
        log.info("qwsDTO={}", qwsDTO);
        FeynmanTaskEntity feynmanTaskEntity = feynmanBusiness.builderFeynmanTask(qwsDTO.getUuid(), FEYNMAN_TYPE, qwsDTO.getExecutor());
        try {
            feynmanBusiness.saveFeynmanTask(feynmanTaskEntity);
            String fileName = feynmanService.plot(qwsDTO);
            QwsResultDTO build = QwsResultDTO.builder().fileName(fileName)
                    .build();
            QwsEntity qwsEntity = new QwsEntity();
            BeanUtils.copyProperties(qwsDTO, qwsEntity);
            qwsService.save(qwsEntity);
            feynmanBusiness.updateFeynmanTaskSuccess(feynmanTaskEntity);
            return Result.success(build);
        } catch (Exception ex) {
            feynmanBusiness.updateFeynmanTaskFailed(feynmanTaskEntity);
            throw ex;
        }

    }

    @PostMapping(value = "/plotfile")
    public Result<QwsResultDTO> plotFile(@RequestBody QwsFileDTO qwsFileDTO) throws Exception {
        log.info("qwsFileDTO={}", qwsFileDTO);
        FeynmanTaskEntity feynmanTaskEntity = feynmanBusiness.builderFeynmanTask(qwsFileDTO.getUuid(), FEYNMAN_TYPE, qwsFileDTO.getExecutor());
        feynmanBusiness.saveFeynmanTask(feynmanTaskEntity);
        try {

            String fileName = feynmanService.plotFile(qwsFileDTO);
            QwsResultDTO build = QwsResultDTO.builder().fileName(fileName)
                    .build();
            QwsFileEntity qwsFileEntity = new QwsFileEntity();
            BeanUtils.copyProperties(qwsFileDTO, qwsFileEntity);
            String points = "";
            for (TableDataDTO tableDataDTO : qwsFileDTO.getTabledata()) {
                String pointCoordinate = "(" + tableDataDTO.getX() + "," + tableDataDTO.getY() + ")";
                points = points + pointCoordinate + ", ";
            }
            points = points.substring(0, points.length() - 2);
            qwsFileEntity.setPoints(points);
            qwsFileService.save(qwsFileEntity);
            feynmanBusiness.updateFeynmanTaskSuccess(feynmanTaskEntity);
            return Result.success(build);
        } catch (Exception ex) {
            feynmanBusiness.updateFeynmanTaskFailed(feynmanTaskEntity);
            throw ex;
        }
    }

    @PostMapping(value = "/qws/result")
    @ResponseBody
    public Result<List<Demo>> selectAll() throws Exception {
        List<QwsEntity> result = qwsService.selectAll();
        List<Demo> list = new ArrayList<>();
        for (QwsEntity qwsEntity : result) {
            Demo demo = new Demo();
            demo.setId(qwsEntity.getId());
            demo.setZ(qwsEntity.getZ());
            demo.setX((qwsEntity.getX()));
            demo.setY(qwsEntity.getY());
            demo.setDx(qwsEntity.getDx());
            demo.setDy(qwsEntity.getDy());
            demo.setPx(qwsEntity.getPx());
            demo.setPy(qwsEntity.getPy());
            demo.setFz(qwsEntity.getFz());
            demo.setInn(qwsEntity.getInn());
            demo.setUuid(qwsEntity.getUuid());
            list.add(demo);
        }
        return Result.success(list);
    }

    @PostMapping(value = "/qwsFile/result")
    @ResponseBody
    public Result<List<QwsFileDtoRebuild>> getPoints() throws Exception {
        List<QwsFileEntity> result = qwsFileService.getPoints();
        List<QwsFileDtoRebuild> list = new ArrayList<>();
        for (QwsFileEntity qwsFileEntity : result) {
            QwsFileDtoRebuild qwsFileDtoRebuild = new QwsFileDtoRebuild();
            qwsFileDtoRebuild.setId(qwsFileEntity.getId());
            qwsFileDtoRebuild.setUuid(qwsFileEntity.getUuid());
            qwsFileDtoRebuild.setFz(qwsFileEntity.getFz());
            qwsFileDtoRebuild.setInn(qwsFileEntity.getInn());
            qwsFileDtoRebuild.setPoints(qwsFileEntity.getPoints());
            list.add(qwsFileDtoRebuild);
        }
        return Result.success(list);
    }
}
