package com.jackie.bloghandle.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: 转码代码
 * @ClassName: ExecuteCodecs
 * @description: 用ffmpeg进行文件转码
 * @author: jackie
 * @create: 2019-06-13 14:37
 * @VERSION:v1.0
 **/

public class ExecuteCodecs {

    private static final Logger logger = LoggerFactory.getLogger(ExecuteCodecs.class);

    //ffmpegPath的路径
    private static final String ffmpegPath = "D:\\develop\\ffmpeg\\bin\\ffmpeg.exe";

    private static final String frmpPahtFormat = "rtmp://127.0.0.1/live/";

    /**
     * 视频转码 (PC端MP4)
     *
     * @param rtspPath 用于转码的rtsp路劲
     * @return rtmpPath   转码后产生的rtmp播放路劲
     * @throws Exception
     */
    public static String exchangeRtsp2Rtmp(String rtspPath) {
        String rtmpPath = frmpPahtFormat + "00000012081310000001";
        // 创建List集合来保存转换视频文件为flv格式的命令
        List<String> convert = new ArrayList<String>();
        convert.add(ffmpegPath); // 添加转换工具路径
        convert.add("-i");
        convert.add(rtspPath);
        convert.add("-c copy");
        convert.add("-f flv");
        convert.add("-r 25");
        convert.add("-s 1920*1080");
        //convert.add("-vf");   // 添加水印
        //convert.add("movie=watermark.gif[wm];[in][wm]overlay=20:20[out]");
        convert.add(rtmpPath);


        try {
            Process videoProcess = new ProcessBuilder(convert).redirectErrorStream(true).start();
//            new PrintStream(videoProcess.getInputStream()).start();
            //videoProcess.waitFor();  // 加上这句，系统会等待转换完成。不加，就会在服务器后台自行转换。

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return rtmpPath;
    }


}
