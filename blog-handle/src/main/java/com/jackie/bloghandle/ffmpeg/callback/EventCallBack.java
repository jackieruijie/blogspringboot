package com.jackie.bloghandle.ffmpeg.callback;

import com.jackie.bloghandle.ffmpeg.data.CommandTasker;

/**
 * 事件回调
 *
 * @author jackie
 * @version 1.0
 * @since jdk1.8
 */
public interface EventCallBack {

    /**
     * 命令行执行开始事件
     *
     * @param t      -事件类型
     * @param tasker -任务信息
     */
    boolean callback(EventCallBackType t, CommandTasker tasker);
}
