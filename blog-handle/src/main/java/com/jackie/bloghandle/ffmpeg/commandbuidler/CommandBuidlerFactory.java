package com.jackie.bloghandle.ffmpeg.commandbuidler;

/**
 * 默认流式命令构建器工厂类
 * @author jackie
 * @since jdk1.8
 * @version 1.0
 *
 */
public class CommandBuidlerFactory {

	public static CommandBuidler createBuidler() {
		return new DefaultCommandBuidler();
	};
	
	public static  CommandBuidler createBuidler(String rootpath) {
		return new DefaultCommandBuidler(rootpath);
	};
}
