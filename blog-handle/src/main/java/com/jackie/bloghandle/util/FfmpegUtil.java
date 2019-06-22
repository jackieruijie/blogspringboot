package com.jackie.bloghandle.util;

import java.io.IOException;

/**
 * @program: jsdz
 * @ClassName: FfmpegUtil
 * @description: FfmpegUtil
 * @author: jackie
 * @create: 2019-06-13 14:59
 * @VERSION:v1.0
 **/

public class FfmpegUtil {

    /**
     * 对rtsp直播流进行转码，
     * @param rtspUrl rtsp源文件路径
     * @param rtmpAppName  rtmp应用名称
     */
    public static String rtmp2Rtmp(String rtspUrl,String rtmpAppName){
        StringBuffer nginxServer=new StringBuffer("rtmp://"+IpUtils.getLocalIp()+"/live/");//获取本机nginx-rtmp服务地址
        if (!"".equals(rtmpAppName)){
            nginxServer.append(rtmpAppName);//拼接rtmp文件路劲
        }else {
            return "rtmp应用名称为空，无法转码";
        }
        Process process=null;
        StringBuffer str=new StringBuffer(500);
        //str.append("/usr/local/ffmpeg/bin/./ffmpeg ");//linux
        str.append("D:/develop/ffmpeg/bin/ffmpeg.exe ");//windows
        str.append("-i ");
        str.append(rtspUrl);
        str.append(" -c copy ");
//        str.append(" -c:v libx264 ");
//        str.append("  -preset fast ");
        str.append(" -f flv ");
        str.append(" -r 25 ");
        str.append(" -s 1920*1080 ");
        str.append("  ");
        str.append(nginxServer.toString());
        try {
            System.out.println("change rtsp to rtmp codde---->"+str.toString());
            process=Runtime.getRuntime().exec(str.toString());//执行转码命令
            System.out.println("wav change to wav success！！！！");
//            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return nginxServer.toString();
    }

}
