package com.qutronix.cloud.feynmanserver.controller;


import com.mathworks.toolbox.javabuilder.MWException;
import com.qutronix.cloud.feynmanserver.dto.Demo;
import com.qutronix.cloud.feynmanserver.dto.QwsDTO;
import com.qutronix.cloud.feynmanserver.dto.QwsFileDTO;
import com.qutronix.cloud.feynmanserver.dto.QwsResultDTO;
import com.qutronix.cloud.feynmanserver.entity.QwsEntity;
import com.qutronix.cloud.feynmanserver.service.FeynmanService;
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

    /**
     * 测试代码
     */
    @PostMapping("/test")
    @ApiOperation(value = "测试代码")
    public R list() throws Exception {
        ApplicationHome applicationHome = new ApplicationHome();
        log.info("applicationHome:{}", applicationHome.getDir().getAbsolutePath());
        feynmanService.feynmanTest();
        return R.ok();
    }

    @GetMapping(value = "/result", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public BufferedImage getImage(@RequestParam String fileName) throws IOException {
        return ImageIO.read(new FileInputStream(new File("F:\\qutronix\\images\\" + fileName + ".png")));
    }


    @PostMapping(value = "/plot")
    public Result<QwsResultDTO> plot(@RequestBody QwsDTO qwsDTO) throws Exception {
        log.info("qwsDTO={}", qwsDTO);
        try {
//            String fileName = feynmanService.plot(qwsDTO);
            QwsResultDTO build = QwsResultDTO.builder().fileName(qwsDTO.getUuid())
                    .build();
            QwsEntity qwsEntity = new QwsEntity();
            BeanUtils.copyProperties(qwsDTO,qwsEntity);
            qwsService.save(qwsEntity);
            return Result.success(build);
        } catch (Exception ex) {
            throw new Exception("Failed to plot:" + ex.getMessage());
        }

    }

    @PostMapping(value = "/plotfile")
    public Result<QwsResultDTO> plotFile(@RequestBody QwsFileDTO qwsFileDTO) throws Exception {
        log.info("qwsFileDTO={}", qwsFileDTO);
        String fileName = feynmanService.plotFile(qwsFileDTO);
        QwsResultDTO build = QwsResultDTO.builder().fileName(fileName)
                .build();
        return Result.success(build);

    }

    @PostMapping(value = "/qws/result")
    @ResponseBody
    public Result<List<Demo>> selectAll() throws Exception {
        List<QwsEntity> result = qwsService.selectAll();
        List<Demo> list = new ArrayList<>();
        for (QwsEntity qwsEntity : result ) {
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
}
