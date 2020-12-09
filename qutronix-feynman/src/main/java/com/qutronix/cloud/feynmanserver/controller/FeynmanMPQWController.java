package com.qutronix.cloud.feynmanserver.controller;

import com.mathworks.toolbox.javabuilder.MWException;
import com.qutronix.cloud.feynmanserver.business.FeynmanBusiness;
import com.qutronix.cloud.feynmanserver.config.FeynmanConfig;
import com.qutronix.cloud.feynmanserver.dto.BS_dataForm;
import com.qutronix.cloud.feynmanserver.dto.MPQW_dataForm;
import com.qutronix.cloud.feynmanserver.dto.MPResultDTO;
import com.qutronix.cloud.feynmanserver.dto.QwsResultDTO;
import com.qutronix.cloud.feynmanserver.entity.FeynmanTaskEntity;
import com.qutronix.cloud.feynmanserver.entity.MPQwsEntity;
import com.qutronix.cloud.feynmanserver.service.FeynmanService4;
import com.qutronix.cloud.feynmanserver.service.MPQwsService;
import com.qutronix.common.result.Result;
import com.qutronix.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.redisson.RedissonSubList;
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
@RequestMapping("feynman/server4")
@Api(tags = "思量云FeynmanServer4")
@Slf4j
public class FeynmanMPQWController {

    @Autowired
    FeynmanService4 feynmanService4;

    @Autowired
    MPQwsService mpQwsService;

    @Autowired
    FeynmanBusiness feynmanBusiness;

    @Autowired
    private FeynmanConfig feynmanConfig;


    private static final String FEYNMAN_TYPE = "multi";

    /**
     * Demo Code For Multi-Particle Quantum Walks...
     */

    @PostMapping("/test")
    @ApiOperation(value = "MPQW_Test")
    public R list() throws Exception {
        ApplicationHome applicationHome = new ApplicationHome();
        log.info("applicationHome:{}", applicationHome.getDir().getAbsolutePath());
        feynmanService4.feynmanTest();
        return R.ok();
    }

    @GetMapping(value = "/result", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public BufferedImage getImage(@RequestParam String fileName) throws IOException {
        log.info("result fileName={}", fileName);
        return ImageIO.read(
                new FileInputStream(
                        new File(feynmanConfig.filePath + fileName + feynmanConfig.fileSuffix)
                ));
    }

    @PostMapping(value = "/MPQW_plot1")
    public Result<MPResultDTO> plot1(@RequestBody MPQW_dataForm mpqw_dataForm)
            throws IOException, MWException {
        log.info("mpqw_dataForm={}", mpqw_dataForm);
        FeynmanTaskEntity feynmanTaskEntity = feynmanBusiness.builderFeynmanTask(mpqw_dataForm.getUuid(), FEYNMAN_TYPE, mpqw_dataForm.getExecutor());

        try {
            feynmanBusiness.saveFeynmanTask(feynmanTaskEntity);
            MPResultDTO mpResultDTO = feynmanService4.plot1(mpqw_dataForm);
            MPQwsEntity mpQwsEntity = new MPQwsEntity();
            BeanUtils.copyProperties(mpqw_dataForm, mpQwsEntity);
            String refinedIniState = mpqw_dataForm.getIniState();
            refinedIniState = refinedIniState.substring(1, refinedIniState.length() - 1);
            mpQwsEntity.setIniRawState(refinedIniState);
            String refinedProbState = mpqw_dataForm.getProbStates();
            refinedProbState = refinedProbState.replace(">", "]");
            mpQwsEntity.setProbRawStates(refinedProbState);
            mpQwsService.save(mpQwsEntity);
            feynmanBusiness.updateFeynmanTaskSuccess(feynmanTaskEntity);
            return Result.success(mpResultDTO);
        } catch (Exception ex) {
            feynmanBusiness.updateFeynmanTaskFailed(feynmanTaskEntity);
            throw ex;
        }
    }

    @PostMapping(value = "MPQws/result")
    @ResponseBody
    public Result<List<MPQwsEntity>> returnAll() throws Exception {
        List<MPQwsEntity> list = mpQwsService.returnAll();
        return Result.success(list);
    }
}
