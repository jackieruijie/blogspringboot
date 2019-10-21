package com.jackie.bloghandle.ffmpeg.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class IpUtils {

    //判断本机操作系统
    public static boolean isWindowOS() {
        boolean isWindowOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindowOS = true;
        }
        return isWindowOS;
    }

    public static String getLocalIp() {
        if (isWindowOS()) {
            return getWindowsRealIP();
        } else {
            return getLinuxLocalIp();
        }
    }


    //获取linux操作系统的IP地址
    public static String getLinuxLocalIp() {
        Enumeration allNetInterfaces = null;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        InetAddress ip = null;
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
            Enumeration addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                ip = (InetAddress) addresses.nextElement();
                if (ip != null && ip instanceof Inet4Address) {
                    if ("enp3s0".equals(netInterface.getName())) {
                        System.out.println("--------------------------- ");
                        System.out.println("--------------------------- ");
                        System.out.println("本机的IP = " + ip.getHostAddress());
                        System.out.println("--------------------------- ");
                        System.out.println("--------------------------- ");
                        return ip.getHostAddress();
                    } else if ("eth0".equals(netInterface.getName())) {
                        System.out.println("--------------------------- ");
                        System.out.println("--------------------------- ");
                        System.out.println("本机的IP = " + ip.getHostAddress());
                        System.out.println("--------------------------- ");
                        System.out.println("--------------------------- ");
                        return ip.getHostAddress();
                    }
                }
            }
        }
        return null;
    }


    /**
     * 获取windows操作系统的IP地址
     * 获取本地真正的IP地址，即获得有线或者无线WiFi地址。
     * 过滤虚拟机、蓝牙等地址
     *
     * @return
     */
    public static String getWindowsRealIP() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
                        .nextElement();

                // 去除回环接口，子接口，未运行和接口
                if (netInterface.isLoopback() || netInterface.isVirtual()
                        || !netInterface.isUp()) {
                    continue;
                }

                if (!netInterface.getDisplayName().contains("Intel")
                        && !netInterface.getDisplayName().contains("Realtek")) {
                    continue;
                }
                Enumeration<InetAddress> addresses = netInterface
                        .getInetAddresses();
                System.out.println(netInterface.getDisplayName());
                while (addresses.hasMoreElements()) {
                    InetAddress ip = addresses.nextElement();
                    if (ip != null) {
                        // ipv4
                        if (ip instanceof Inet4Address) {
                            System.out.println("本机ipv4------------------>" + ip.getHostAddress());
                            return ip.getHostAddress();
                        }
                    }
                }
                break;
            }
        } catch (SocketException e) {
            System.err.println("Error when getting host ip address"
                    + e.getMessage());
        }
        return null;

    }


}
