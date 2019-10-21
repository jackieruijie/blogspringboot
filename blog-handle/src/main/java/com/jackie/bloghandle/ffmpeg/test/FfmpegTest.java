package com.jackie.bloghandle.ffmpeg.test;


import com.jackie.bloghandle.ffmpeg.service.FfmpegService;

/**
 * @program: jsdz
 * @ClassName: FfmpegTest
 * @description: FfmpegTest
 * @author: jackie
 * @create: 2019-06-24 15:27
 * @VERSION:v1.0
 **/

public class FfmpegTest {
    public static void main(String[] args) {

        try {
            FfmpegTest.startTest();
            Thread.sleep(50000);
            FfmpegTest.stopTest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void startTest() {
        String rtspUrl = "rtsp://192.168.1.130:8554/00000012081310000001/0";
        String rtmpUrl = FfmpegService.rtsp2Rtmp(rtspUrl);
        System.out.println(rtmpUrl);
    }

    public static void stopTest() {
        String rtspUrl = "rtsp://192.168.1.130:8554/00000012081310000001/0";
        FfmpegService.stopTcoding(rtspUrl);

    }


}
