package com.qutronix.cloud.util;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author zhongqikuan
 * @email zhong.qikuan@163.com
 * @date 2020/10/30 15:15
 */
@Slf4j
public class PythonUtil {
    public static String python(String pythonPath, String[] params, Channel channel)throws Exception {
        File file = new File(pythonPath);
        if (!file.exists()) {
            return "python脚本不存在！";
        }

        String[] command = Arrays.copyOf(new String[]{"python", pythonPath}, params.length + 2);
        System.arraycopy(params, 0, command, 2, params.length);

        List

                res = new ArrayList<>();
        try {
            Process process = Runtime.getRuntime().exec(command, null, null);
            // 标准输入流（必须写在 waitFor 之前）
            String inStr = consumeInputStream(process.getInputStream(),channel);
            log.info("inStr={}",inStr);
            // 标准错误流（必须写在 waitFor 之前）
            String errStr = consumeInputStream(process.getErrorStream(),channel);
            log.info("errStr={}",errStr);
            process.waitFor();

            Scanner scanner = new Scanner(process.getInputStream());
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                log.info("line={}", line);

                res.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "success";
    }

    /**
     * 消费inputstream，并返回
     */
    public static String consumeInputStream(InputStream is,Channel channel) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = br.readLine()) != null) {
            log.info(s);
            channel.writeAndFlush(new TextWebSocketFrame(s+"\n"));
            sb.append(s);
        }
        return sb.toString();
    }
}
