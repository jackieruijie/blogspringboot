package com.jackie.bloghandle.handler;

public class Test {
    public static void main(String[] args) {
        ConvertVideoPakcet beginTrasCoding=new ConvertVideoPakcet();
        beginTrasCoding.inputRtspPath("rtsp://192.168.1.130:8554/00000012081310000002/0")
                .rtmpOutPath("rtmp://192.168.1.50/live/00000012081310000002").printLogs().go();
    }

    public void show(){


    }




}