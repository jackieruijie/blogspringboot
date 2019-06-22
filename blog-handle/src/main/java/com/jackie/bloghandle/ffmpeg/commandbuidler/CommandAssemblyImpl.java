package com.jackie.bloghandle.ffmpeg.commandbuidler;

import java.util.Map;
/**
 * 默认命令组装器实现
 * @author jackie
 * @since jdk1.8
 * @version 1.0
 */
public class CommandAssemblyImpl implements CommandAssembly{
	/**
	 * 组装ffmpeg要执行的命令
	 * @param paramMap 	-要组装的map
	 * @return  ffmpeg要执行的命令
	 */
	public String assembly(Map<String, String> paramMap) {
		try {
			if (paramMap.containsKey("ffmpegPath")) {
				String ffmpegPath = paramMap.get("ffmpegPath");
				// -i：输入流地址或者文件绝对地址
				StringBuilder comm = new StringBuilder(ffmpegPath + " -i ");
				/**
				 * 是否有必输项：
				 * input-->输入地址
				 * output-->输出地址
				 * appName-->应用名
				 * twoPart：0-推一个元码流；1-推一个自定义推流；2-推两个流（一个是自定义，一个是元码）
				 */
				if (paramMap.containsKey("input") && paramMap.containsKey("output") && paramMap.containsKey("appName")
						&& paramMap.containsKey("twoPart")) {
					String input =  paramMap.get("input");
					String output =  paramMap.get("output");
					String appName = paramMap.get("appName");
					String twoPart =  paramMap.get("twoPart");
					String codec =  paramMap.get("codec");
					// 默认h264解码
					codec = (codec == null ? "h264" : paramMap.get("codec"));
					// 输入地址
					comm.append(input);
					// 当twoPart为0时，只推一个元码流
					if ("0".equals(twoPart)) {
						comm.append(" -vcodec " + codec + " -f flv -an " + output + appName);
					} else {
						// -f ：转换格式，默认flv
						if (paramMap.containsKey("fmt")) {
							comm.append(" -f " +  paramMap.get("fmt"));
						}
						// -r :帧率，默认25；-g :帧间隔
						if (paramMap.containsKey("fps")) {
							comm.append(" -r " + paramMap.get("fps"));
							comm.append(" -g " + paramMap.get("fps"));
						}
						// -s 分辨率 默认是原分辨率
						if (paramMap.containsKey("rs")) {
							comm.append(" -s " + paramMap.get("rs"));
						}
						// 输出地址+发布的应用名
						comm.append(" -an " + output + appName);
						// 当twoPart为2时推两个流，一个自定义流，一个元码流
						if ("2".equals(twoPart)) {
							// 一个视频源，可以有多个输出，第二个输出为拷贝源视频输出，不改变视频的各项参数并且命名为应用名+HD
							comm.append(" -vcodec copy  -f flv -an ").append(output + appName + "HD");
						}
					}
					return comm.toString();
				}
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	@Override
	public String assembly() {
		return null;
	}
}
