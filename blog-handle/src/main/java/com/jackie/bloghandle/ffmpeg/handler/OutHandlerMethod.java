package com.jackie.bloghandle.ffmpeg.handler;
/**
 * 输出消息处理
 * @author jackie
 * @since jdk1.8
 * @version 1.0
 */
public interface OutHandlerMethod {
	/**
	 * 解析消息
	 * @param id-任务ID
	 * @param msg -消息
	 */
	public void parse(String id, String msg);
	
	/**
	 * 任务是否异常中断
	 * @return
	 */
	public boolean isbroken();
}
