package com.jackie.bloghandle.ffmpeg.service;


import com.jackie.bloghandle.ffmpeg.CommandManager;
import com.jackie.bloghandle.ffmpeg.CommandManagerImpl;
import com.jackie.bloghandle.ffmpeg.data.CommandTasker;
import com.jackie.bloghandle.ffmpeg.util.IpUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: jsdz
 * @ClassName: FfmpegController
 * @description: FfmpegController
 * @author: jackie
 * @create: 2019-06-24 14:49
 * @VERSION:v1.0
 **/

public class FfmpegService {
    private static Map<String, String> rtmpUrlsMap = new HashMap<>();

    /**
     * rtsp转Rtmp接口
     *
     * @throws InterruptedException
     */
    public static String rtsp2Rtmp(String rtspUrl) {
        StringBuffer nginxServer = new StringBuffer("rtmp://" + IpUtils.getLocalIp() + "/live/");//获取本机nginx-rtmp服务地址
        String rtmpAppName = rtspUrl.substring(rtspUrl.trim().lastIndexOf("/") - 20, rtspUrl.trim().lastIndexOf("/"));//获取应用名称
        String rtmpUrl = null;
        if (rtmpUrlsMap.containsKey(rtmpAppName)) {
            return rtmpUrlsMap.get(rtmpAppName);
        } else {
            rtmpUrl = nginxServer.toString() + rtmpAppName;
        }

        CommandManager manager = new CommandManagerImpl();
        Map<String, String> map = new HashMap<>();
        map.put("appName", rtmpAppName);
        map.put("input", rtspUrl.trim());
        map.put("output", rtmpUrl);
        map.put("codec", "h264");
        map.put("fmt", "flv");
        map.put("-c:v", "copy");
        map.put("-c:a", "copy");
        map.put("fps", "30");
        map.put("rs", "1920*1080");
//        map.put("twoPart", "2");
        // 执行任务，id就是appName，如果执行失败返回为null
        String id = manager.start(map);//开始执行转码程序
        if (id != null && !"".equals(id)) {
            System.out.println("当前任务-------->" + id + "执行成功！！！");
            rtmpUrlsMap.put(id, rtmpUrl);//存放当前已转码的内容
            return rtmpUrl;
        }
        return null;
    }


    /**
     * @param rtspUrl
     * @return
     */
    public static void stopTcoding(String rtspUrl) {
        CommandManager manager = new CommandManagerImpl();
        String id = rtspUrl.substring(rtspUrl.trim().lastIndexOf("/") - 20, rtspUrl.trim().lastIndexOf("/"));
        // 通过id查询
        CommandTasker info = manager.query(id);
        System.out.println(info);
        System.out.println("当前任务" + id + "的信息为：" + info);
        // 查询全部
        Collection<CommandTasker> infoList = manager.queryAll();
        System.out.println("当前执行的全部任务------->" + infoList);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 停止id对应的任务
        manager.stop(id);
        rtmpUrlsMap.remove(id);
    }


}
