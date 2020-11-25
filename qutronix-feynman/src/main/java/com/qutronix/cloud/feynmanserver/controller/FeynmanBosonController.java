package com.qutronix.cloud.feynmanserver.controller;

import com.mathworks.toolbox.javabuilder.MWException;
import com.qutronix.cloud.feynmanserver.dto.BS_dataForm;
import com.qutronix.cloud.feynmanserver.dto.QwsResultDTO;
import com.qutronix.cloud.feynmanserver.dto.TwoD_Qws;
import com.qutronix.cloud.feynmanserver.service.FeynmanService3;
import com.qutronix.common.result.Result;
import com.qutronix.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("feynman/server3")
@Api(tags = "思量云FeynmanServer3")
@Slf4j
public class FeynmanBosonController {

    @Autowired
    FeynmanService3 feynmanService;

    /**
     * Demo Code For Boson Sampling...
     */

    @PostMapping("/test")
    @ApiOperation(value = "BosonSamplingTest")
    public R list() throws  Exception{
        ApplicationHome applicationHome = new ApplicationHome();
        log.info ("applicationHome:{}",applicationHome.getDir().getAbsolutePath());
        feynmanService.feynmanTest();
        return R.ok();
    }

    @GetMapping(value = "/result",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public BufferedImage getImage(@RequestParam String fileName) throws IOException {
        return ImageIO.read(
                new FileInputStream(
                        new File("/Users/mingyuexu/Desktop/TestPics2/"+fileName+".jpg")
                ));
    }

    @PostMapping (value = "/Boson_plot1")
    public Result<QwsResultDTO> plot1(@RequestBody BS_dataForm bs_dataForm)
            throws IOException, MWException {
        log.info("BS_dataForm={}",bs_dataForm);

        String fileName = feynmanService.plot1(bs_dataForm);
        QwsResultDTO build = QwsResultDTO.builder().fileName(fileName)
                .build();
        return Result.success(build);
    }

    @PostMapping (value = "/Boson_plot2")
    public Result<QwsResultDTO> plot2(@RequestBody BS_dataForm bs_dataForm)
            throws IOException, MWException {
        log.info("BS_dataForm={}",bs_dataForm);

        String fileName = feynmanService.plot2(bs_dataForm);
        QwsResultDTO build = QwsResultDTO.builder().fileName(fileName)
                .build();
        return Result.success(build);
    }

    @PostMapping (value = "/Boson_plot3")
    public Result<QwsResultDTO> plot3(@RequestBody BS_dataForm bs_dataForm)
            throws IOException, MWException {
        log.info("BS_dataForm={}",bs_dataForm);

        String fileName = feynmanService.plot3(bs_dataForm);
        QwsResultDTO build = QwsResultDTO.builder().fileName(fileName)
                .build();
        return Result.success(build);
    }
}
