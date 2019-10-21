package com.jackie.bloghandle.handler;

import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.avformat;
import org.bytedeco.javacv.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.bytedeco.javacpp.avcodec.AV_CODEC_ID_RA_144;
import static org.bytedeco.javacpp.avcodec.av_free_packet;

/**
 * 通过获取到的rtsp转码成rtmp流
 */
public class ConvertVideoPakcet {
    FFmpegFrameGrabber grabber = null;//拉取

    FFmpegFrameRecorder record = null;//发布

    int width = -1, height = -1; //获取视频的宽度和高度

    // 视频参数
    protected int videoCodec; //视频编码方式
    protected double framerate;// 帧率
    protected int bitrate;// 比特率


    // 想要录制音频，这三个参数必须有：audioChannels > 0 && audioBitrate > 0 && sampleRate > 0
    private int audioChannels; //音频通道
    private int audioBitrate; //音频比特率
    private int sampleRate; //音频采样率
    // 音频参数
    private int audioCodec;//音频编码方式
    private double audioFramerate;//音频帧率
    private String audioCodecName;//音频编码名称

    /**
     * 拉取rtsp流，获得流的信息
     *
     * @param inputRtspPath 要获取的rtsp地址
     * @return
     */
    public ConvertVideoPakcet inputRtspPath(String inputRtspPath) {
        try {
            grabber = new FFmpegFrameGrabber(inputRtspPath);//拉取rtsp流
            //封装ffmpeg需要额外添加的命令
            if (inputRtspPath.indexOf("rtsp") >= 0) grabber.setOption("rtsp_transport", "tcp");
            //添加时间戳
            grabber.setTimestamp(System.currentTimeMillis());
            //开始执行ffmpeg命令，之后就可以获取视频和音频信息
            grabber.start();
            if (width < 0 || height < 0) {
                width = grabber.getImageWidth();
                height = grabber.getImageWidth();
            }
            Frame frame = grabber.grabFrame();
            //获取视频参数
            videoCodec = grabber.getVideoCodec();
            bitrate = grabber.getVideoBitrate();
            framerate = grabber.getVideoFrameRate();
            //获取音频参数
            audioBitrate = grabber.getAudioBitrate();
            audioChannels = grabber.getAudioChannels();
            sampleRate = grabber.getSampleRate();
            audioCodec = grabber.getAudioCodec();
            audioFramerate = grabber.getAudioFrameRate();
            audioCodecName = grabber.getAudioCodecName();
            if (audioBitrate < 1) audioBitrate = 128 * 1000;//默认音频比特流
            return this;
        } catch (FrameGrabber.Exception e) {
            if (grabber == null) System.err.println("获取编码信息发生异常，异常信息为：" + e.getMessage());
            e.printStackTrace();
            return this;
        }

    }

    /**
     * 推流，将获取到的rtsp流推到rtmp服务器中
     *
     * @param rtmpPath rtmp服务器应用地址
     * @return
     */
    public ConvertVideoPakcet rtmpOutPath(String rtmpPath) {
        record = new FFmpegFrameRecorder(rtmpPath, width, height);
        record.setOption("crf", "25");
        record.setOption("bsf:a", "aac_adtstoasc");
        //设置视频参数
        record.setGopSize(4);
        record.setFrameRate(framerate);   //pam_alaw   acc
        record.setFrameRate(audioFramerate);   //pam_alaw   acc
        record.setVideoBitrate(bitrate);
        //设置音频参数
        /*音频通道 ：1
        音频比特率 ：64000 ----> 65536
        音频采样率 ：8000
        音频编码方式 ：65543
        音频帧率 ：12.5--->25*/
        avformat.AVFormatContext avFc = null;
        if (rtmpPath.indexOf("rtmp") >= 0 || rtmpPath.indexOf("flv") >= 0) {
            record.setFormat("flv");
            avFc = grabber.getFormatContext();
        }
        try {
            record.start(avFc);
        } catch (FrameRecorder.Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 转封装
     *
     * @throws IOException     
     * @author eguid
     */
    public ConvertVideoPakcet go() {
        printLogsWithTimer(false);
        long err_index = 0;//采集或推流导致的错误次数
        //连续五次没有采集到帧则认为视频采集结束，程序错误次数超过1次即中断程序
        for (int no_frame_index = 0; no_frame_index < 5 || err_index > 1; ) {
            avcodec.AVPacket pkt = null;
            try {
                //没有解码的音视频帧
                pkt = grabber.grabPacket();
                if (pkt == null || pkt.size() <= 0 || pkt.data() == null) {
                    //空包记录次数跳过
                    no_frame_index++;
                    continue;
                }
                //不需要编码直接把音视频帧推出去
                err_index += (record.recordPacket(pkt) ? 0 : 1);//如果失败err_index自增1
                av_free_packet(pkt);
            } catch (FrameGrabber.Exception e) {//推流失败
                err_index++;
            } catch (IOException e) {
                err_index++;
            }
        }
        try {
            grabber.close();
            record.close();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        } catch (FrameRecorder.Exception e) {
            e.printStackTrace();
            printLogsWithTimer(true);
        }
        printLogsWithTimer(true);
        return this;
    }

    /**
     * 打印基本信息
     *
     * @return
     */
    public ConvertVideoPakcet printLogs() {
        System.err.println("---------- 视频参数 -------------");
        System.err.println(" 视频编码方式 ：" + videoCodec);
        System.err.println(" 帧率 ：" + framerate);
        System.err.println(" 比特率 ：" + bitrate);
        System.err.println("---------- 音频参数 -------------");
        System.err.println(" 音频通道 ：" + audioChannels);
        System.err.println(" 音频比特率 ：" + audioBitrate);
        System.err.println(" 音频采样率 ：" + sampleRate);
        System.err.println(" 音频编码方式 ：" + audioCodec);
        System.err.println(" 音频帧率 ：" + audioFramerate);
        System.err.println(" 音频编码名称 ：" + audioCodecName);
        return this;
    }

    /**
     * 定时打印转码信息
     */
    public void printLogsWithTimer(boolean isEnd) {
        System.err.println(new SimpleDateFormat("yyyy-mm-dd HH:MM:ss.sss").format(new Date()) + " INFO--> :" +
                " frame =  " + record.getFrameRate() + " size = " + record.getGopSize() + " audioBitrate =" + record.getAudioBitrate() + " videoBitrate= " + record.getVideoBitrate() + "");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (isEnd) return;
                printLogsWithTimer(false);
            }
        }, 2000);//120分钟统计一次
    }

}
