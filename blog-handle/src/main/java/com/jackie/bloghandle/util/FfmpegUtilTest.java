package com.jackie.bloghandle.util;

public class FfmpegUtilTest {
    public static void main(String[] args) {
        String rtspUrl = "rtsp://192.168.1.130:8554/00000012081310000001/0".trim();
        String rtmpAppName = rtspUrl.substring(rtspUrl.lastIndexOf("/") - 20, rtspUrl.lastIndexOf("/"));
        System.out.println(rtmpAppName);
        String rtmpUrl = FfmpegUtil.rtmp2Rtmp(rtspUrl, rtmpAppName);
        System.out.println("rtmpUrl-------->" + rtmpUrl);
    }
}
