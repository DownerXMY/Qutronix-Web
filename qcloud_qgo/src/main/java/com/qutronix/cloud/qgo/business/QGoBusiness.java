package com.qutronix.cloud.qgo.business;

import com.qutronix.cloud.util.PythonUtil;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



/**
 * @author zhongqikuan
 * @email zhong.qikuan@163.com
 * @date 2020/10/30 15:17
 */
@Service
@Slf4j
public class QGoBusiness {


    public static void beginQGo(Channel channel,Integer num) throws Exception {
        String command = "E:\\qutronix\\project\\code\\quantumGO 4\\bot_vs_bot.py";
        String[] params = {num.toString()};
        log.info(PythonUtil.python(command, params,channel));
    }
}
