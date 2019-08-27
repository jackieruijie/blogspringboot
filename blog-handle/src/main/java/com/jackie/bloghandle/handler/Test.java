package com.jackie.bloghandle.handler;

public class Test {
    public static void main(String[] args) {
        ConvertVideoPakcet beginTrasCoding=new ConvertVideoPakcet();
        beginTrasCoding.inputRtspPath("rtsp://101.200.49.0:8554/00000047071310001227/0")
                .rtmpOutPath("rtmp://192.168.1.50/live/00000047071310001227").printLogs().go();
    }
}
