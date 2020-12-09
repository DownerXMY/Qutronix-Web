package com.qutronix.cloud.feynmanserver.controller;

import com.mathworks.toolbox.javabuilder.MWException;
import com.qutronix.cloud.feynmanserver.business.FeynmanBusiness;
import com.qutronix.cloud.feynmanserver.config.FeynmanConfig;
import com.qutronix.cloud.feynmanserver.dao.BosonDao;
import com.qutronix.cloud.feynmanserver.dto.BS_dataForm;
import com.qutronix.cloud.feynmanserver.dto.BosonResultDTO;
import com.qutronix.cloud.feynmanserver.dto.QwsResultDTO;
import com.qutronix.cloud.feynmanserver.dto.TwoD_Qws;
import com.qutronix.cloud.feynmanserver.entity.BosonEntity;
import com.qutronix.cloud.feynmanserver.entity.FeynmanTaskEntity;
import com.qutronix.cloud.feynmanserver.service.BosonService;
import com.qutronix.cloud.feynmanserver.service.FeynmanService3;
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
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("feynman/server3")
@Api(tags = "思量云FeynmanServer3")
@Slf4j
public class FeynmanBosonController {

    @Autowired
    FeynmanService3 feynmanService;

    @Autowired
    BosonService bosonService;

    @Autowired
    FeynmanBusiness feynmanBusiness;

    @Autowired
    private FeynmanConfig feynmanConfig;

    private static final String FEYNMAN_TYPE = "boson";

    /**
     * Demo Code For Boson Sampling...
     */

    @PostMapping("/test")
    @ApiOperation(value = "BosonSamplingTest")
    public R list() throws Exception {
        ApplicationHome applicationHome = new ApplicationHome();
        log.info("applicationHome:{}", applicationHome.getDir().getAbsolutePath());
        feynmanService.feynmanTest();
        return R.ok();
    }

    @GetMapping(value = "/result", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public BufferedImage getImage(@RequestParam String fileName) throws IOException {
        return ImageIO.read(
                new FileInputStream(
                        new File(feynmanConfig.filePath + fileName + ".jpg")
                ));
    }

    @PostMapping(value = "/Boson_plot1")
    public Result<QwsResultDTO> plot1(@RequestBody BS_dataForm bs_dataForm)
            throws IOException, MWException {
        log.info("BS_dataForm={}", bs_dataForm);

        String fileName = feynmanService.plot1(bs_dataForm);
        QwsResultDTO build = QwsResultDTO.builder().fileName(fileName)
                .build();
        return Result.success(build);
    }

    @PostMapping(value = "/example")
    public Result<BosonResultDTO> plot2(@RequestBody BS_dataForm bs_dataForm)
            throws IOException, MWException {
        log.info("BS_dataForm={}", bs_dataForm);
        FeynmanTaskEntity feynmanTaskEntity = feynmanBusiness.builderFeynmanTask(bs_dataForm.getUuid(), FEYNMAN_TYPE, bs_dataForm.getExecutor());
        try {
            feynmanBusiness.saveFeynmanTask(feynmanTaskEntity);
            BosonResultDTO bosonResultDTO = feynmanService.plot2(bs_dataForm);
            BosonEntity bosonEntity = new BosonEntity();
            BeanUtils.copyProperties(bs_dataForm, bosonEntity);
            String refinedState = bs_dataForm.getIniState();
            refinedState = refinedState.substring(1, refinedState.length() - 1);
            bosonEntity.setIniRawState(refinedState);
            bosonService.save(bosonEntity);
            feynmanBusiness.updateFeynmanTaskSuccess(feynmanTaskEntity);
            return Result.success(bosonResultDTO);
        } catch (Exception ex) {
            feynmanBusiness.updateFeynmanTaskFailed(feynmanTaskEntity);
            throw ex;
        }
    }

    @PostMapping(value = "/Boson_plot3")
    public Result<QwsResultDTO> plot3(@RequestBody BS_dataForm bs_dataForm)
            throws IOException, MWException {
        log.info("BS_dataForm={}", bs_dataForm);

        String fileName = feynmanService.plot3(bs_dataForm);
        QwsResultDTO build = QwsResultDTO.builder().fileName(fileName)
                .build();
        return Result.success(build);
    }

    @PostMapping(value = "/Boson/result")
    @ResponseBody
    public Result<List<BosonEntity>> returnAll() throws Exception {
        List<BosonEntity> result = bosonService.returnAll();
        return Result.success(result);
    }
}
