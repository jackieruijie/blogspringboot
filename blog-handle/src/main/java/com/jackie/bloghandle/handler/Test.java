package com.jackie.bloghandle.handler;

public class Test {
    public static void main(String[] args) {
        ConvertVideoPakcet beginTrasCoding=new ConvertVideoPakcet();
        beginTrasCoding.inputRtspPath("rtsp://192.168.1.102/phone.sdp")
                .rtmpOutPath("rtmp://192.168.1.50/live/phone0001").printLogs().go();
    }
}
