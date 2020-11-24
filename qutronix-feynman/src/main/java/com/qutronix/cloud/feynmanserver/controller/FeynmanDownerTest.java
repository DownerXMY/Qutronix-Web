package com.qutronix.cloud.feynmanserver.controller;

import com.mathworks.toolbox.javabuilder.MWException;
import com.qutronix.cloud.feynmanserver.dto.QwsDTO;
import com.qutronix.cloud.feynmanserver.dto.QwsResultDTO;
import com.qutronix.cloud.feynmanserver.dto.TwoD_Qws;
import com.qutronix.cloud.feynmanserver.service.FeynmanService;
import com.qutronix.cloud.feynmanserver.service.FeynmanService2;
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
@RequestMapping("feynman/server2")
@Api(tags = "思量云FeynmanServer2")
@Slf4j
public class FeynmanDownerTest {

    @Autowired
    FeynmanService2 feynmanService;

    /**
     * Demo Code For 2D Quantum Stochastic Walks...
     */
    @PostMapping("/test")
    @ApiOperation(value = "DownerTest")
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
                new File("F:\\qutronix\\images\\"+fileName+".jpg")
                ));
    }


    @PostMapping (value = "/plot2")
    public Result<QwsResultDTO> plot2(@RequestBody TwoD_Qws twoD_qws)
            throws IOException, MWException {
        log.info("qwsDTO={}",twoD_qws);

        String fileName = feynmanService.plot2(twoD_qws);
        QwsResultDTO build = QwsResultDTO.builder().fileName(fileName)
                .build();
        return Result.success(build);
    }

    @PostMapping (value = "/plot3")
    public Result<QwsResultDTO> plot3(@RequestBody TwoD_Qws twoD_qws)
            throws IOException, MWException {
        log.info("qwsDTO={}",twoD_qws);

        String fileName = feynmanService.plot3(twoD_qws);
        QwsResultDTO build = QwsResultDTO.builder().fileName(fileName)
                .build();
        return Result.success(build);
    }
}
