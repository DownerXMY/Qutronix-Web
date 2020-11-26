package com.qutronix.cloud.feynmanserver.controller;

import com.mathworks.toolbox.javabuilder.MWException;
import com.qutronix.cloud.feynmanserver.dto.BS_dataForm;
import com.qutronix.cloud.feynmanserver.dto.MPQW_dataForm;
import com.qutronix.cloud.feynmanserver.dto.QwsResultDTO;
import com.qutronix.cloud.feynmanserver.service.FeynmanService4;
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
@RequestMapping("feynman/server4")
@Api(tags = "思量云FeynmanServer4")
@Slf4j
public class FeynmanMPQWController {

    @Autowired
    FeynmanService4 feynmanService4;

    /**
     * Demo Code For Multi-Particle Quantum Walks...
     */

    @PostMapping("/test")
    @ApiOperation(value = "MPQW_Test")
    public R list() throws  Exception{
        ApplicationHome applicationHome = new ApplicationHome();
        log.info ("applicationHome:{}",applicationHome.getDir().getAbsolutePath());
        feynmanService4.feynmanTest();
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

    @PostMapping (value = "/MPQW_plot1")
    public Result<QwsResultDTO> plot1(@RequestBody MPQW_dataForm mpqw_dataForm)
            throws IOException, MWException {
        log.info("MPQW_dataForm={}",mpqw_dataForm);

        String fileName = feynmanService4.plot1(mpqw_dataForm);
        QwsResultDTO build = QwsResultDTO.builder().fileName(fileName)
                .build();
        return Result.success(build);
    }
}
