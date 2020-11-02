package com.qutronix.cloud.qgo.handler;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MyChannelHandlerPool
 * 通道组池，管理所有websocket连接
 * @author zhongqikuan
 */
public class MyChannelHandlerPool {

    public MyChannelHandlerPool(){}

    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static Map<String,Channel> channelsMap = new HashMap<>();

}