package com.jackie.bloghandle.handler;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过获取到的rtsp转码成rtmp流
 */
public  class ConvertVideoPakcet {
    FFmpegFrameGrabber grabber = null;//拉取

    FFmpegFrameRecorder record = null;//发布

    int width = -1, height = -1; //获取视频的宽度和高度

    // 视频参数
    protected int videocodecid;
    protected int codecid;
    protected double framerate;// 帧率
    protected int bitrate;// 比特率

    // 音频参数
    // 想要录制音频，这三个参数必须有：audioChannels > 0 && audioBitrate > 0 && sampleRate > 0
    private int audioChannels;
    private int audioBitrate;
    private int sampleRate;

    /**
     * 拉取rtsp流，获得流的信息
     * @param inputRtspPath
     * @return
     */
    public  ConvertVideoPakcet inputRtspPath(String inputRtspPath){
        grabber=new FFmpegFrameGrabber(inputRtspPath);//拉取rtsp流
        //封装ffmpeg需要额外添加的命令
        Map<String,String> ffmpegCode=new HashMap<>();
        ffmpegCode.put("rtsp_transport","tcp");

        if(inputRtspPath.indexOf("rtsp")>0) grabber.setOptions(ffmpegCode);
        try {
            //添加时间戳
            grabber.setAudioTimestamp(System.currentTimeMillis());
            grabber.setVideoTimestamp(System.currentTimeMillis());
            grabber.setTimestamp(System.currentTimeMillis());
            //开始执行ffmpeg命令，之后就可以获取视频和音频信息
            grabber.start();
            if (width<0||height<0){
                width=grabber.getImageWidth();
                height=grabber.getImageWidth();
            }
            Frame frame=grabber.grabFrame();
            //获取视频参数



        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }

        return this;
    }

}
