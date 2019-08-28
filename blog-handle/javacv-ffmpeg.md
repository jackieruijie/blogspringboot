# javacv ffmpeg

## 1. FFmpegFrameGrabber

#### FFmpegFrameGrabber.java

```java
public class FFmpegFrameGrabber extends FrameGrabber {
    private static Exception loadingException = null;
    static Map<Pointer, InputStream> inputStreams;
    static FFmpegFrameGrabber.ReadCallback readCallback;
    static FFmpegFrameGrabber.SeekCallback seekCallback;
    private InputStream inputStream;
    private int maximumSize;
    private AVIOContext avio;
    private String filename;
    private AVFormatContext oc;
    private AVStream video_st;
    private AVStream audio_st;
    private AVCodecContext video_c;
    private AVCodecContext audio_c;
    private AVFrame picture;
    private AVFrame picture_rgb;
    private BytePointer[] image_ptr;
    private Buffer[] image_buf;
    private AVFrame samples_frame;
    private BytePointer[] samples_ptr;
    private Buffer[] samples_buf;
    private BytePointer[] samples_ptr_out;
    private Buffer[] samples_buf_out;
    private AVPacket pkt;
    private AVPacket pkt2;
    private int sizeof_pkt;
    private int[] got_frame;
    private SwsContext img_convert_ctx;
    private SwrContext samples_convert_ctx;
    private int samples_channels;
    private int samples_format;
    private int samples_rate;
    private boolean frameGrabbed;
    private Frame frame;
}
```



#### FrameGrabber.java

```java
public abstract class FrameGrabber implements Closeable {
    public static final List<String> list = new LinkedList(Arrays.asList("DC1394", "FlyCapture", "FlyCapture2", "OpenKinect", "OpenKinect2", "RealSense", "PS3Eye", "VideoInput", "OpenCV", "FFmpeg", "IPCamera"));
    public static final long SENSOR_PATTERN_RGGB = 0L;
    public static final long SENSOR_PATTERN_GBRG = 4294967296L;
    public static final long SENSOR_PATTERN_GRBG = 1L;
    public static final long SENSOR_PATTERN_BGGR = 4294967297L;
    protected int videoStream = -1;
    protected int audioStream = -1;
    protected String format = null;
    protected String videoCodecName = null;
    protected String audioCodecName = null;
    protected int imageWidth = 0;
    protected int imageHeight = 0;
    protected int audioChannels = 0;
    protected FrameGrabber.ImageMode imageMode;
    protected long sensorPattern;
    protected int pixelFormat;
    protected int videoCodec;
    protected int videoBitrate;
    protected int imageScalingFlags;
    protected double aspectRatio;
    protected double frameRate;
    protected FrameGrabber.SampleMode sampleMode;
    protected int sampleFormat;
    protected int audioCodec;
    protected int audioBitrate;
    protected int sampleRate;
    protected boolean triggerMode;
    protected int bpp;
    protected int timeout;
    protected int numBuffers;
    protected double gamma;
    protected boolean deinterlace;
    protected Map<String, String> options;
    protected Map<String, String> videoOptions;
    protected Map<String, String> audioOptions;
    protected Map<String, String> metadata;
    protected Map<String, String> videoMetadata;
    protected Map<String, String> audioMetadata;
    protected int frameNumber;
    protected long timestamp;
    protected int maxDelay;
    private ExecutorService executor;
    private Future<Void> future;
    private Frame delayedFrame;
    private long delayedTime;
}
```



## 2. FFmpegFrameRecorder

#### FFmpegFrameRecorder.java

```java
public class FFmpegFrameRecorder extends FrameRecorder {
    private static Exception loadingException = null;
    static Map<Pointer, OutputStream> outputStreams;
    static FFmpegFrameRecorder.WriteCallback writeCallback;
    private OutputStream outputStream;
    private AVIOContext avio;
    private String filename;
    private AVFrame picture;
    private AVFrame tmp_picture;
    private BytePointer picture_buf;
    private BytePointer video_outbuf;
    private int video_outbuf_size;
    private AVFrame frame;
    private Pointer[] samples_in;
    private BytePointer[] samples_out;
    private PointerPointer samples_in_ptr;
    private PointerPointer samples_out_ptr;
    private BytePointer audio_outbuf;
    private int audio_outbuf_size;
    private int audio_input_frame_size;
    private AVOutputFormat oformat;
    private AVFormatContext oc;
    private AVCodec video_codec;
    private AVCodec audio_codec;
    private AVCodecContext video_c;
    private AVCodecContext audio_c;
    private AVStream video_st;
    private AVStream audio_st;
    private SwsContext img_convert_ctx;
    private SwrContext samples_convert_ctx;
    private int samples_channels;
    private int samples_format;
    private int samples_rate;
    private AVPacket video_pkt;
    private AVPacket audio_pkt;
    private int[] got_video_packet;
    private int[] got_audio_packet;
    private AVFormatContext ifmt_ctx;
}
```

#### FrameRecorder.java

```java
public abstract class FrameRecorder implements Closeable {
    public static final List<String> list = new LinkedList(Arrays.asList("FFmpeg", "OpenCV"));
    protected String format;
    protected String videoCodecName;
    protected String audioCodecName;
    protected int imageWidth;
    protected int imageHeight;
    protected int audioChannels;
    protected int pixelFormat;
    protected int videoCodec;
    protected int videoBitrate;
    protected int imageScalingFlags;
    protected int gopSize = -1;
    protected double aspectRatio;
    protected double frameRate;
    protected double videoQuality = -1.0D;
    protected int sampleFormat;
    protected int audioCodec;
    protected int audioBitrate;
    protected int sampleRate;
    protected double audioQuality = -1.0D;
    protected boolean interleaved;
    protected Map<String, String> options = new HashMap();
    protected Map<String, String> videoOptions = new HashMap();
    protected Map<String, String> audioOptions = new HashMap();
    protected Map<String, String> metadata = new HashMap();
    protected Map<String, String> videoMetadata = new HashMap();
    protected Map<String, String> audioMetadata = new HashMap();
    protected int frameNumber = 0;
    protected long timestamp = 0L;
    protected int maxBFrames = -1;
    protected int trellis = -1;
    protected int maxDelay = -1;
}
```







# 参数对比





## 自制流

```tex
---------- 视频参数 -------------
 视频编码方式 ：27
 帧率 ：19.0
 比特率 ：0
---------- 音频参数 -------------
 音频通道 ：0
 音频比特率 ：128000
 音频采样率 ：8000
 音频编码方式 ：86018
 音频帧率 ：7.8125
 音频编码名称 ：null

```

## 公司

```tex
---------- 视频参数 -------------
 视频编码方式 ：27
 帧率 ：90000.0
 比特率 ：0
---------- 音频参数 -------------
 音频通道 ：1
 音频比特率 ：64000
 音频采样率 ：8000
 音频编码方式 ：65543
 音频帧率 ：12.5
 音频编码名称 ：null
```



```shell
C:\Users\Administrator> ffmpeg -rtsp_transport tcp -i rtsp://192.168.1.102/phone.sdp  -c:a copy -c:v copy -f flv -r 35 -s 1920*1080 -bsf:a aac_adtstoasc  rtmp://192.168.1.50/live/phone0001
ffmpeg version N-94652-g808a6717e0 Copyright (c) 2000-2019 the FFmpeg developers
  built with gcc 9.1.1 (GCC) 20190807
  configuration: --enable-gpl --enable-version3 --enable-sdl2 --enable-fontconfig --enable-gnutls --enable-iconv --enable-libass --enable-libdav1d --enable-libbluray --enable-libfreetype --enable-libmp3lame --enable-libopencore-amrnb --enable-libopencore-amrwb --enable-libopenjpeg --enable-libopus --enable-libshine --enable-libsnappy --enable-libsoxr --enable-libtheora --enable-libtwolame --enable-libvpx --enable-libwavpack --enable-libwebp --enable-libx264 --enable-libx265 --enable-libxml2 --enable-libzimg --enable-lzma --enable-zlib --enable-gmp --enable-libvidstab --enable-libvorbis --enable-libvo-amrwbenc --enable-libmysofa --enable-libspeex --enable-libxvid --enable-libaom --enable-libmfx --enable-amf --enable-ffnvcodec --enable-cuvid --enable-d3d11va --enable-nvenc --enable-nvdec --enable-dxva2 --enable-avisynth --enable-libopenmpt
  libavutil      56. 33.100 / 56. 33.100
  libavcodec     58. 55.101 / 58. 55.101
  libavformat    58. 31.104 / 58. 31.104
  libavdevice    58.  9.100 / 58.  9.100
  libavfilter     7. 58.101 /  7. 58.101
  libswscale      5.  6.100 /  5.  6.100
  libswresample   3.  6.100 /  3.  6.100
  libpostproc    55.  6.100 / 55.  6.100
[rtsp @ 00000278c21c9540] Missing PPS in sprop-parameter-sets, ignoring
[h264 @ 00000278c21cd380] corrupted macroblock 22 39 (total_coeff=-1)
[h264 @ 00000278c21cd380] error while decoding MB 22 39
[h264 @ 00000278c21cd380] concealing 50 DC, 50 AC, 50 MV errors in I frame
Input #0, rtsp, from 'rtsp://192.168.1.102/phone.sdp':
  Metadata:
    title           : EasyDarwin
    comment         : EasyDarwin
  Duration: N/A, start: 0.000000, bitrate: N/A
    Stream #0:0: Video: h264 (Baseline), yuv420p(tv, unknown/bt470bg/unknown, progressive), 360x640, 19.25 tbr, 90k tbn, 180k tbc
    Stream #0:1: Audio: aac (LC), 8000 Hz, mono, fltp
Output #0, flv, to 'rtmp://192.168.1.50/live/phone0001':
  Metadata:
    title           : EasyDarwin
    comment         : EasyDarwin
    encoder         : Lavf58.31.104
    Stream #0:0: Video: h264 (Baseline) ([7][0][0][0] / 0x0007), yuv420p(tv, unknown/bt470bg/unknown, progressive), 360x640, q=2-31, 19.25 tbr, 1k tbn, 35 tbc
    Stream #0:1: Audio: aac (LC) ([10][0][0][0] / 0x000A), 8000 Hz, mono, fltp
Stream mapping:  
  Stream #0:0 -> #0:0 (copy)
  Stream #0:1 -> #0:1 (copy)
Press [q] to stop, [?] for help
[flv @ 00000278c2506580] Timestamps are unset in a packet for stream 0. This is deprecated and will stop working in the future. Fix your code to set the timestamps properly
[flv @ 00000278c2506580] Failed to update header with correct duration.1.6kbits/s speed=1.01x
[flv @ 00000278c2506580] Failed to update header with correct filesize.
frame= 5023 fps= 19 q=-1.0 Lsize=   15609kB time=00:04:25.32 bitrate= 482.0kbits/s speed=1.01x
video:14958kB audio:518kB subtitle:0kB other streams:0kB global headers:0kB muxing overhead: 0.859028%
```

