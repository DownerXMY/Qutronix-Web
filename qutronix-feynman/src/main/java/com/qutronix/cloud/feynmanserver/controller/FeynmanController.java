package com.qutronix.cloud.feynmanserver.controller;


import com.qutronix.cloud.feynmanserver.service.FeynmanService;
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

    /**
     * 测试代码
     */
    @PostMapping("/test")
    @ApiOperation(value = "测试代码")
    public R list() throws  Exception{
        ApplicationHome applicationHome = new ApplicationHome();
      log.info ("applicationHome:{}",applicationHome.getDir().getAbsolutePath());
        feynmanService.feynmanTest();
        return R.ok();
    }
    @GetMapping(value = "/result",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public BufferedImage getImage() throws IOException {
        return ImageIO.read(new FileInputStream(new File("F:\\qutronix\\images\\tt.png")));
    }




}
