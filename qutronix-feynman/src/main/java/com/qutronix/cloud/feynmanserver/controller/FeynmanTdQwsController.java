package com.qutronix.cloud.feynmanserver.controller;

import com.mathworks.toolbox.javabuilder.MWException;
import com.qutronix.cloud.feynmanserver.business.FeynmanBusiness;
import com.qutronix.cloud.feynmanserver.dto.QwsDTO;
import com.qutronix.cloud.feynmanserver.dto.QwsResultDTO;
import com.qutronix.cloud.feynmanserver.dto.TwoD_Qws;
import com.qutronix.cloud.feynmanserver.entity.FeynmanTaskEntity;
import com.qutronix.cloud.feynmanserver.entity.MPQwsEntity;
import com.qutronix.cloud.feynmanserver.entity.TdQwsEntity;
import com.qutronix.cloud.feynmanserver.service.FeynmanService;
import com.qutronix.cloud.feynmanserver.service.FeynmanService2;
import com.qutronix.cloud.feynmanserver.service.TdQwsService;
import com.qutronix.common.result.Result;
import com.qutronix.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("feynman/server2")
@Api(tags = "思量云FeynmanServer2")
@Slf4j
public class FeynmanTdQwsController {
    @Autowired
    FeynmanBusiness feynmanBusiness;
    @Autowired
    FeynmanService2 feynmanService;

    @Autowired
    TdQwsService tdQwsService;

    private static final String FEYNMAN_TYPE = "qsws";

    /**
     * Demo Code For 2D Quantum Stochastic Walks...
     */
    @PostMapping("/test")
    @ApiOperation(value = "DownerTest")
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
                        new File("F:\\qutronix\\images\\" + fileName + ".jpg")
                ));
    }


    @PostMapping(value = "/plot2")
    public Result<QwsResultDTO> plot2(@RequestBody TwoD_Qws twoD_qws)
            throws IOException, MWException {
        log.info("qwsDTO={}", twoD_qws);

        String fileName = feynmanService.plot2(twoD_qws);
        QwsResultDTO build = QwsResultDTO.builder().fileName(fileName)
                .build();
        return Result.success(build);
    }

    @PostMapping(value = "/plot3")
    public Result<QwsResultDTO> plot3(@RequestBody TwoD_Qws twoD_qws)
            throws IOException, MWException {
        log.info("qwsDTO={}", twoD_qws);
        FeynmanTaskEntity feynmanTaskEntity = feynmanBusiness.builderFeynmanTask(twoD_qws.getUuid(), FEYNMAN_TYPE, twoD_qws.getExecutor());
        try {
            feynmanBusiness.saveFeynmanTask(feynmanTaskEntity);
            QwsResultDTO build = feynmanService.plot3(twoD_qws);
            TdQwsEntity tdQwsEntity = new TdQwsEntity();
            BeanUtils.copyProperties(twoD_qws, tdQwsEntity);
            tdQwsService.save(tdQwsEntity);
            feynmanBusiness.updateFeynmanTaskSuccess(feynmanTaskEntity);
            return Result.success(build);
        } catch (Exception ex) {
            feynmanBusiness.updateFeynmanTaskFailed(feynmanTaskEntity);
            throw ex;
        }
    }

    @PostMapping(value = "/TdQws/result")
    @ResponseBody
    public Result<List<TdQwsEntity>> returnAll() throws Exception {
        List<TdQwsEntity> list = tdQwsService.returnAll();
        return Result.success(list);
    }
}
